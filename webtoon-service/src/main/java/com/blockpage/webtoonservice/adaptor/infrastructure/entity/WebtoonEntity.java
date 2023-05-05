package com.blockpage.webtoonservice.adaptor.infrastructure.entity;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class WebtoonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String webtoonTitle;
    @Column
    private String webtoonDescription;
    @Column
    private String creator;
    @Column
    private String illustrator;
    @Column
    @Enumerated(EnumType.STRING)
    private PublicationDays publicationDays;
    @Column
    private String webtoonMainImage;
    @Column
    private String webtoonThumbnail;
    @Column
    private int interestCount;
    @Column
    @Enumerated(EnumType.STRING)
    private GenreType genreType;
    @Column
    @Enumerated(EnumType.STRING)
    private WebtoonStatus webtoonStatus;
    @Column
    private Long creatorId;
    @Column
    private int views;


}
