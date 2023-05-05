package com.blockpage.webtoonservice.adaptor.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import lombok.Getter;

@Entity
@Getter
public class EpisodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private WebtoonEntity webtoon;
    @Column
    private String episodeTitle;
    @Column
    private String episodeThumbnail;
    @Column
    private Date uploadDate;
    @Column
    private String authorWords;
    @Column
    private int totalScore;
    @Column
    private int participantCount;
    @Column
    private String episodeStatus;
    @Column
    private int episodePrice;
    @Column
    private int episodeNumber;



}
