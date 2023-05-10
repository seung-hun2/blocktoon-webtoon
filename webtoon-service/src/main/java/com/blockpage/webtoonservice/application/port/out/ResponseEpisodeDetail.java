package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseEpisodeDetail {

    private List<ResponseEpisodeImage> images;
    private Long episodeId;
    private int commentCount;
    private double rating;
    private String author;
    private String authorWords;
    private String nextEpisodeTitle;
    private String nextEpisodeThumbnail;

    public static ResponseEpisodeDetail toResponseFromEntity(EpisodeEntity episode, List<ImageEntity> imageEntityList) {
        return ResponseEpisodeDetail.builder()
            .commentCount(episode.getCommentCount())
            .episodeId(episode.getId())
            .rating(9.9)
            .author(episode.getWebtoon().getCreator())
            .authorWords(episode.getAuthorWords())
            .nextEpisodeTitle("두개 들고오기 기억하자")
            .nextEpisodeThumbnail("두개 들고오기 사진 기억하자")
            .images(imageEntityList.stream()
                .map(ResponseEpisodeImage::toResponseFromEntity)
                .collect(Collectors.toList())
            )
            .build();
    }
}
