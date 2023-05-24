package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.domain.Episode;
import lombok.Getter;

@Getter
public class ResponseCreatorEpisode {

    private String episodeTitle;
    private Integer episodeNumber;
    private String episodeThumbnail;
    private Integer totalScore;
    private Integer participantCount;
    private String uploadDate;


    public ResponseCreatorEpisode(String episodeTitle, Integer episodeNumber, String episodeThumbnail, Integer totalScore,
        Integer participantCount, String uploadDate) {
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
            episode.getUploadDate().toString());
    }

    public static ResponseCreatorEpisode toResponseFromDomain(Episode episode) {
        return new ResponseCreatorEpisode(
            episode.getEpisodeTitle(),
            episode.getEpisodeNumber(),
            episode.getEpisodeThumbnail(),
            episode.getTotalScore(),
            episode.getParticipantCount(),
            episode.getUploadDate());
    }

}
