package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import java.sql.Date;
import lombok.Getter;

@Getter
public class EpisodeView {

    private String episodeTitle;
    private String episodeThumbnail;
    private Date uploadDate;
    private int totalScore;


    public EpisodeView(String episodeTitle, String episodeThumbnail, Date uploadDate, int totalScore) {
        this.episodeTitle = episodeTitle;
        this.episodeThumbnail = episodeThumbnail;
        this.uploadDate = uploadDate;
        this.totalScore = totalScore;
    }

    public static EpisodeView toViewFromResponse(ResponseEpisode responseEpisode) {
        return new EpisodeView(
            responseEpisode.getEpisodeTitle(),
            responseEpisode.getEpisodeThumbnail(),
            responseEpisode.getUploadDate(),
            responseEpisode.getTotalScore());
    }

}
