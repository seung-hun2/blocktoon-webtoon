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
    private String webtoonStatus;
    private String authorWords;


    public ResponseCreatorEpisode(String episodeTitle, Integer episodeNumber, String episodeThumbnail, Integer totalScore,
        Integer participantCount, String uploadDate, String webtoonStatus, String authorWords) {
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.episodeThumbnail = episodeThumbnail;
        this.totalScore = totalScore;
        this.participantCount = participantCount;
        this.uploadDate = uploadDate;
        this.webtoonStatus = webtoonStatus;
        this.authorWords = authorWords;
    }


    public static ResponseCreatorEpisode toResponseFromDomain(Episode episode) {
        return new ResponseCreatorEpisode(
            episode.getEpisodeTitle(),
            episode.getEpisodeNumber(),
            episode.getEpisodeThumbnail(),
            episode.getTotalScore(),
            episode.getParticipantCount(),
            episode.getUploadDate(),
            episode.getWebtoonStatus(),
            episode.getAuthorWords());
    }

}
