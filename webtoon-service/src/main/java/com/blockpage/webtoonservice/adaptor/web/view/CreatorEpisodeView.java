package com.blockpage.webtoonservice.adaptor.web.view;

import lombok.Getter;

@Getter
public class CreatorEpisodeView {

    private String episodeTitle;
    private int episodeNumber;
    private String episodeThumbnail;
    private int totalScore;
    private int participantCount;
    private String uploadDate;


    public CreatorEpisodeView(String episodeTitle, int episodeNumber, String episodeThumbnail, int totalScore, int participantCount,
        String uploadDate) {
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.episodeThumbnail = episodeThumbnail;
        this.totalScore = totalScore;
        this.participantCount = participantCount;
        this.uploadDate = uploadDate;
    }


}
