package com.blockpage.webtoonservice.application.service;

import com.blockpage.webtoonservice.application.port.in.EpisodeUseCase;
import com.blockpage.webtoonservice.application.port.in.RatingUseCase;
import com.blockpage.webtoonservice.application.port.out.EpisodePort;
import com.blockpage.webtoonservice.application.port.out.ResponseCreatorEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import com.blockpage.webtoonservice.domain.Episode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpisodeService implements EpisodeUseCase, RatingUseCase {

    private final EpisodePort episodePort;

    @Override
    public List<ResponseEpisode> findEpisode(Long webtoonId, String sort) {

        List<Episode> episodeList = episodePort.findEpisode(webtoonId, sort);

        return episodeList.stream().map(ResponseEpisode::toResponseFromDomain).toList();
    }

    @Override
    public List<ResponseCreatorEpisode> findCreatorEpisode(Long webtoonId) {
        List<Episode> episodeList = episodePort.findCreatorEpisode(webtoonId);
        return episodeList.stream().map(ResponseCreatorEpisode::toResponseFromDomain).toList();
    }

    @Override
    public ResponseEpisodeDetail findEpisodeDetail(Long episodeId, Long webtoonId, Integer episodeNumber) {
        Episode episode = episodePort.findEpisodeDetail(episodeId, webtoonId, episodeNumber);

        return ResponseEpisodeDetail.toResponseFromDomain(episode);
    }

    @Override
    public void updateRating(RatingQuery ratingQuery) {
        episodePort.updateRating(ratingQuery.getEpisodeId(), ratingQuery.getTotalScore(), ratingQuery.getParticipantCount());
    }
}
