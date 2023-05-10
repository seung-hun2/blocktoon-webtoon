package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseCreatorEpisode;
import java.sql.Date;
import lombok.Getter;

@Getter
public class CreatorEpisodeView {

    private String episodeTitle;
    private int episodeNumber;
    private String episodeThumbnail;
    private int totalScore;
    private int participantCount;
    private Date uploadDate;


    public CreatorEpisodeView(String episodeTitle, int episodeNumber, String episodeThumbnail, int totalScore, int participantCount,
        Date uploadDate) {
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.episodeThumbnail = episodeThumbnail;
        this.totalScore = totalScore;
        this.participantCount = participantCount;
        this.uploadDate = uploadDate;
    }

    public static CreatorEpisodeView toViewFromResponse(ResponseCreatorEpisode responseCreatorEpisode) {
        return new CreatorEpisodeView(
            responseCreatorEpisode.getEpisodeTitle(),
            responseCreatorEpisode.getEpisodeNumber(),
            responseCreatorEpisode.getEpisodeThumbnail(),
            responseCreatorEpisode.getTotalScore(),
            responseCreatorEpisode.getParticipantCount(),
            responseCreatorEpisode.getUploadDate());
    }


}
