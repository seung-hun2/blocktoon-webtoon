package com.blockpage.webtoonservice.adaptor.web.view;

import java.sql.Date;
import lombok.Getter;

@Getter
public class EpisodeView {

    private String episodeTitle;
    private String episodeThumbnail;
    private String uploadDate;
    private int totalScore;


    public EpisodeView(String episodeTitle, String episodeThumbnail, String uploadDate, int totalScore) {
        this.episodeTitle = episodeTitle;
        this.episodeThumbnail = episodeThumbnail;
        this.uploadDate = uploadDate;
        this.totalScore = totalScore;
    }
}
