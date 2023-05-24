package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.domain.Episode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseEpisodeImage {

    private String episodeImage;

    public static ResponseEpisodeImage toResponseFromEntity(ImageEntity imageEntity) {
        return ResponseEpisodeImage.builder()
            .episodeImage(imageEntity.getImage())
            .build();
    }

    public static ResponseEpisodeImage toResponseFromDomain(Episode.Image image) {
        return ResponseEpisodeImage.builder()
            .episodeImage(image.getImages())
            .build();
    }

}
