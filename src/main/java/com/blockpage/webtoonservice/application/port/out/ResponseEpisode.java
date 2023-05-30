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
    private Integer totalScore;


    public ResponseEpisode(Long episodeId, Integer episodeNumber, String episodeTitle, String episodeThumbnail, String uploadDate,
        Integer totalScore) {
        this.episodeId = episodeId;
        this.episodeNumber = episodeNumber;
        this.episodeTitle = episodeTitle;
        this.episodeThumbnail = episodeThumbnail;
        this.uploadDate = uploadDate;
        this.totalScore = totalScore;
    }

    public static ResponseEpisode toResponseFromEntity(EpisodeEntity episode) {
        return new ResponseEpisode(
            episode.getId(),
            episode.getEpisodeNumber(),
            episode.getEpisodeTitle(),
            episode.getEpisodeThumbnail(),
            episode.getUploadDate().toString(),
            episode.getTotalScore());
    }

    public static ResponseEpisode toResponseFromDomain(Episode episode) {
        return new ResponseEpisode(
            episode.getEpisodeId(),
            episode.getEpisodeNumber(),
            episode.getEpisodeTitle(),
            episode.getEpisodeThumbnail(),
            episode.getUploadDate(),
            episode.getTotalScore()
        );
    }

}
