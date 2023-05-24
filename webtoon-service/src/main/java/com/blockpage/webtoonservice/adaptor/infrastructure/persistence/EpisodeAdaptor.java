package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.EpisodeRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.ImageRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.application.port.out.EpisodePort;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import com.blockpage.webtoonservice.domain.Episode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EpisodeAdaptor implements EpisodePort {

    private final EpisodeRepository episodeRepository;
    private final ImageRepository imageRepository;
    private final WebtoonRepository webtoonRepository;

    @Override
    public List<Episode> findEpisode(Long webtoonId) {

        List<Episode> episodeList;
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByWebtoonId(webtoonId);
        episodeList = episodeEntityList.stream().map(Episode::toDomainFromEntity).collect(Collectors.toList());

        return episodeList;
    }

    @Override
    public List<Episode> findCreatorEpisode(Long webtoonId) {
        List<Episode> episodeList;
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByWebtoonId(webtoonId);
        episodeList = episodeEntityList.stream().map(Episode::toDomainFromEntity)
            .collect(Collectors.toList());
        return episodeList;
    }

    @Override
    public Episode findEpisodeDetail(Long episodeId, Long webtoonId, Integer episodeNumber) {

        ResponseEpisodeDetail responseEpisodeDetail;
        List<ImageEntity> imageEntityList = imageRepository.findByWebtoonIdAndEpisodeNumber(webtoonId, episodeNumber);
        EpisodeEntity episodeEntity = episodeRepository.findById(episodeId).get();
        String creator = webtoonRepository.findById(episodeId).get().getCreator();

        responseEpisodeDetail = ResponseEpisodeDetail.toResponseFromEntity(episodeEntity, creator, imageEntityList);

        return Episode.toDomainFromResponse(responseEpisodeDetail);
    }
}
