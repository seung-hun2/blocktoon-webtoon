package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import java.sql.Date;
import lombok.Getter;

@Getter
public class ResponseEpisode {

    private String episodeTitle;
    private String episodeThumbnail;
    private Date uploadDate;
    private int totalScore;


    public ResponseEpisode(String episodeTitle, String episodeThumbnail, Date uploadDate, int totalScore) {
        this.episodeTitle = episodeTitle;
        this.episodeThumbnail = episodeThumbnail;
        this.uploadDate = uploadDate;
        this.totalScore = totalScore;
    }

    public static ResponseEpisode toResponseFromEntity(EpisodeEntity episode) {
        return new ResponseEpisode(
            episode.getEpisodeTitle(),
            episode.getEpisodeThumbnail(),
            episode.getUploadDate(),
            episode.getTotalScore());
    }

}
