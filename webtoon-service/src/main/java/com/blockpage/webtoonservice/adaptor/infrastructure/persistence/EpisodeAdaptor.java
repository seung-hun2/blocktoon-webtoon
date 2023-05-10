package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.EpisodeRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.ImageRepository;
import com.blockpage.webtoonservice.application.port.out.EpisodePort;
import com.blockpage.webtoonservice.application.port.out.ResponseCreatorEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EpisodeAdaptor implements EpisodePort {

    private final EpisodeRepository episodeRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<ResponseEpisode> findEpisode(Long webtoonId) {

        List<ResponseEpisode> responseEpisodeList;
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByWebtoonId(webtoonId);
        responseEpisodeList = episodeEntityList.stream().map(ResponseEpisode::toResponseFromEntity).collect(Collectors.toList());

        return responseEpisodeList;
    }

    @Override
    public List<ResponseCreatorEpisode> findCreatorEpisode(Long webtoonId) {
        List<ResponseCreatorEpisode> responseCreatorEpisodeList;
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByWebtoonId(webtoonId);
        responseCreatorEpisodeList = episodeEntityList.stream().map(ResponseCreatorEpisode::toResponseFromEntity)
            .collect(Collectors.toList());
        return responseCreatorEpisodeList;
    }

    @Override
    public ResponseEpisodeDetail findEpisodeDetail(Long episodeId) {

        ResponseEpisodeDetail responseEpisodeDetail;
        List<ImageEntity> imageEntityList = imageRepository.findByEpisodeId(episodeId);
        EpisodeEntity episode = episodeRepository.findById(episodeId).get();
        responseEpisodeDetail = ResponseEpisodeDetail.toResponseFromEntity(episode, imageEntityList);

        return responseEpisodeDetail;
    }
}
