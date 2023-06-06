package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.domain.Episode;
import lombok.Getter;

@Getter
public class ResponseEpisode {

    private Long episodeId;
    private Integer episodeNumber;
    private String episodeTitle;
    private String episodeThumbnail;
    private String uploadDate;
    private double rating;
    private Integer episodePrice;


    public ResponseEpisode(Long episodeId, Integer episodeNumber, String episodeTitle, String episodeThumbnail, String uploadDate,
        double rating, Integer episodePrice) {
        this.episodeId = episodeId;
        this.episodeNumber = episodeNumber;
        this.episodeTitle = episodeTitle;
        this.episodeThumbnail = episodeThumbnail;
        this.uploadDate = uploadDate;
        this.rating = rating;
        this.episodePrice = episodePrice;
    }

    public static ResponseEpisode toResponseFromDomain(Episode episode) {

        return new ResponseEpisode(
            episode.getEpisodeId(),
            episode.getEpisodeNumber(),
            episode.getEpisodeTitle(),
            episode.getEpisodeThumbnail(),
            episode.getUploadDate(),
            episode.getRating(),
            episode.getEpisodePrice()
        );
    }

}
