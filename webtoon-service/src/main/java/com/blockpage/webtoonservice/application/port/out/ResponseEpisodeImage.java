package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseEpisodeImage {

    private String episodeImage;

    public static ResponseEpisodeImage toResponseFromEntity(ImageEntity imageEntity) {
        return ResponseEpisodeImage.builder()
            .episodeImage(imageEntity.getImageUrl())
            .build();
    }
}
