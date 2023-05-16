package com.blockpage.webtoonservice.adaptor.infrastructure.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "episode")
public class EpisodeEntity extends BaseEntity {

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
    @Column
    private int commentCount;


}
