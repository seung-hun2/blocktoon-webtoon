package com.blockpage.webtoonservice.adaptor.infrastructure.entity;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.domain.Demand;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "webtoon")
public class WebtoonEntity extends BaseEntity {

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
    private Integer interestCount;
    @Column
    @Enumerated(EnumType.STRING)
    private GenreType genreType;
    @Column
    @Enumerated(EnumType.STRING)
    private WebtoonStatus webtoonStatus;
    @Column
    private String creatorId;
    @Column
    private Integer views;

    public void update(WebtoonStatus webtoonStatus) {
        this.webtoonStatus = webtoonStatus;
    }

    public void updateViewCount(Integer views) {
        this.views = views;
    }


    public static WebtoonEntity toEntity(Demand demand, String mainUUID, String thumbnailUUID, int type) {
        return WebtoonEntity.builder()
            .creator(demand.getCreator())
            .webtoonStatus(WebtoonStatus.findWebtoonStatusByKey(type))
            .webtoonDescription(demand.getWebtoonDescription())
            .webtoonTitle(demand.getWebtoonTitle())
            .webtoonMainImage(mainUUID)
            .webtoonThumbnail(thumbnailUUID)
            .creatorId(demand.getCreatorId())
            .genreType(demand.getGenre() != null ? GenreType.findGenreTypeByKey(demand.getGenre()) : null)
            .publicationDays(
                demand.getPublicationDays() != null ? PublicationDays.findPublicationDaysByKey(demand.getPublicationDays()) : null)
            .interestCount(0)
            .illustrator(demand.getIllustrator())
            .views(0)
            .build();
    }


}
