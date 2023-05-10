package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import java.sql.Date;
import lombok.Getter;

@Getter
public class ResponseCreatorEpisode {

    private String episodeTitle;
    private int episodeNumber;
    private String episodeThumbnail;
    private int totalScore;
    private int participantCount;
    private Date uploadDate;


    public ResponseCreatorEpisode(String episodeTitle, int episodeNumber, String episodeThumbnail, int totalScore, int participantCount,
        Date uploadDate) {
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.episodeThumbnail = episodeThumbnail;
        this.totalScore = totalScore;
        this.participantCount = participantCount;
        this.uploadDate = uploadDate;
    }

    public static ResponseCreatorEpisode toResponseFromEntity(EpisodeEntity episode) {
        return new ResponseCreatorEpisode(
            episode.getEpisodeTitle(),
            episode.getEpisodeNumber(),
            episode.getEpisodeThumbnail(),
            episode.getTotalScore(),
            episode.getParticipantCount(),
            episode.getUploadDate());
    }

}
