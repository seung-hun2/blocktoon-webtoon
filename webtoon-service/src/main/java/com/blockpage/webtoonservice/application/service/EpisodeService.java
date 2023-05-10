package com.blockpage.webtoonservice.application.service;

import com.blockpage.webtoonservice.adaptor.web.view.CreatorEpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeDetailView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeImageView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeView;
import com.blockpage.webtoonservice.application.port.in.EpisodeUseCase;
import com.blockpage.webtoonservice.application.port.out.EpisodePort;
import com.blockpage.webtoonservice.application.port.out.ResponseCreatorEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpisodeService implements EpisodeUseCase {

    private final EpisodePort episodePort;

    @Override
    public List<EpisodeView> findEpisode(Long webtoonId) {

        List<EpisodeView> episodeViewList;
        List<ResponseEpisode> responseEpisodeList = episodePort.findEpisode(webtoonId);
        episodeViewList = responseEpisodeList.stream().map(EpisodeView::toViewFromResponse).collect(Collectors.toList());

        return episodeViewList;
    }

    @Override
    public List<CreatorEpisodeView> findCreatorEpisode(Long webtoonId) {
        List<CreatorEpisodeView> creatorEpisodeViewList;
        List<ResponseCreatorEpisode> responseCreatorEpisodeList = episodePort.findCreatorEpisode(webtoonId);
        creatorEpisodeViewList = responseCreatorEpisodeList.stream().map(CreatorEpisodeView::toViewFromResponse)
            .collect(Collectors.toList());

        return creatorEpisodeViewList;
    }

    @Override
    public EpisodeDetailView findEpisodeDetail(Long episodeId) {
        EpisodeDetailView episodeDetailView;
        ResponseEpisodeDetail responseEpisodeDetail = episodePort.findEpisodeDetail(episodeId);

        episodeDetailView = EpisodeDetailView.builder()
            .images(responseEpisodeDetail.getImages().stream().map(EpisodeImageView::toViewFromResponse).collect(Collectors.toList()))
            .commentCount(responseEpisodeDetail.getCommentCount())
            .nextEpisodeTitle(responseEpisodeDetail.getNextEpisodeTitle())
            .nextEpisodeThumbnail(responseEpisodeDetail.getNextEpisodeThumbnail())
            .authorWords(responseEpisodeDetail.getAuthorWords())
            .rating(responseEpisodeDetail.getRating())
            .author(responseEpisodeDetail.getAuthor())
            .build();

        return episodeDetailView;
    }

}
