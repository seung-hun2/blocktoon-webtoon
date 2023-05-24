package com.blockpage.webtoonservice.adaptor.web.view;


import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeImage;
import lombok.Getter;

@Getter
public class EpisodeImageView {

    private String imageUrl;

    public EpisodeImageView(String episodeImage) {
        this.imageUrl = episodeImage;
    }


    public static EpisodeImageView toViewFromResponse(ResponseEpisodeImage responseEpisodeImage) {
        return new EpisodeImageView(responseEpisodeImage.getEpisodeImage());
    }

}
