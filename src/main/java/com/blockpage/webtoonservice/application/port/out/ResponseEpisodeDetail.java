package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.domain.Episode;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseEpisodeDetail {

    private List<ResponseEpisodeImage> images;
    private Long episodeId;
    private Integer commentCount;
    private double rating;
    private String author;
    private String authorWords;
    private String nextEpisodeTitle;
    private String nextEpisodeThumbnail;

    public static ResponseEpisodeDetail toResponseFromEntity(EpisodeEntity episode, String creator, List<ImageEntity> imageEntityList,
        String nextTitle, String nextThumbnail) {
        return ResponseEpisodeDetail.builder()
            .commentCount(episode.getCommentCount())
            .episodeId(episode.getId())
            .rating(episode.getParticipantCount() != 0 ? episode.getTotalScore() / episode.getParticipantCount() : 0)
            .author(creator)
            .authorWords(episode.getAuthorWords())
            .nextEpisodeTitle(nextTitle)
            .nextEpisodeThumbnail(nextThumbnail)
            .images(imageEntityList.stream()
                .map(ResponseEpisodeImage::toResponseFromEntity)
                .collect(Collectors.toList())
            )
            .build();
    }

    public static ResponseEpisodeDetail toResponseFromDomain(Episode episode) {
        return ResponseEpisodeDetail.builder()
            .episodeId(episode.getEpisodeId())
            .commentCount(episode.getCommentCount())
            .rating(episode.getRating())
            .author(episode.getAuthor())
            .authorWords(episode.getAuthorWords())
            .nextEpisodeTitle(episode.getNextEpisodeTitle())
            .nextEpisodeThumbnail(episode.getNextEpisodeThumbnail())
            .images(episode.getImages().stream().map(ResponseEpisodeImage::toResponseFromDomain).toList())
            .build();
    }


}
