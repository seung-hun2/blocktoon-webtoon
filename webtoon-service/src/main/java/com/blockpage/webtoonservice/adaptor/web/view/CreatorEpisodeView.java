package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseCreatorEpisode;
import lombok.Getter;

@Getter
public class CreatorEpisodeView {

    private String episodeTitle;
    private Integer episodeNumber;
    private String episodeThumbnail;
    private Integer totalScore;
    private Integer participantCount;
    private String uploadDate;


    public CreatorEpisodeView(String episodeTitle, Integer episodeNumber, String episodeThumbnail, Integer totalScore,
        Integer participantCount, String uploadDate) {
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.episodeThumbnail = episodeThumbnail;
        this.totalScore = totalScore;
        this.participantCount = participantCount;
        this.uploadDate = uploadDate;
    }

    public CreatorEpisodeView(ResponseCreatorEpisode responseCreatorEpisode) {
        this.episodeTitle = responseCreatorEpisode.getEpisodeTitle();
        this.episodeNumber = responseCreatorEpisode.getEpisodeNumber();
        this.episodeThumbnail = responseCreatorEpisode.getEpisodeThumbnail();
        this.totalScore = responseCreatorEpisode.getTotalScore();
        this.participantCount = responseCreatorEpisode.getParticipantCount();
        this.uploadDate = responseCreatorEpisode.getUploadDate();
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
