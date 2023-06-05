package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import lombok.Getter;

@Getter
public class EpisodeView {

    private String episodeTitle;
    private String episodeThumbnail;
    private String uploadDate;
    private double rating;


    public EpisodeView(String episodeTitle, String episodeThumbnail, String uploadDate, double rating) {
        this.episodeTitle = episodeTitle;
        this.episodeThumbnail = episodeThumbnail;
        this.uploadDate = uploadDate;
        this.rating = rating;
    }

    public static EpisodeView toViewFromResponse(ResponseEpisode responseEpisode) {
        return new EpisodeView(
            responseEpisode.getEpisodeTitle(),
            responseEpisode.getEpisodeThumbnail(),
            responseEpisode.getUploadDate(),
            responseEpisode.getRating());
    }

}
