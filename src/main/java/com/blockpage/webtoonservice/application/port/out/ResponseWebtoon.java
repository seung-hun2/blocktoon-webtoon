package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.domain.Webtoon;
import lombok.Getter;

@Getter
public class ResponseWebtoon {

    private Long webtoonId;
    private String webtoonTitle;
    private String webtoonThumbnail;
    private String webtoonMainImage;
    private String description;
    private String creator;
    private String creatorId;
    private String illustrator;
    private Integer views;
    private Integer interestCount;
    private Integer genreType;
    private Integer publicationDays;
    private String webtoonStatus;
    private String cutoutImage;

    public ResponseWebtoon(Long webtoonId, String webtoonTitle, String webtoonThumbnail, String webtoonMainImage, String description,
        String creator, String creatorId, String illustrator, Integer views, Integer interestCount, Integer genreType, Integer publicationDays, String webtoonStatus, String cutoutImage) {
        this.webtoonId = webtoonId;
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.webtoonMainImage = webtoonMainImage;
        this.description = description;
        this.creator = creator;
        this.creatorId = creatorId;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
        this.genreType = genreType;
        this.publicationDays = publicationDays;
        this.webtoonStatus = webtoonStatus;
        this.cutoutImage = cutoutImage;
    }


    public static ResponseWebtoon toResponseFromDomain(Webtoon webtoon) {
        return new ResponseWebtoon(
            webtoon.getWebtoonId(),
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getWebtoonMainImage(),
            webtoon.getWebtoonDescription(),
            webtoon.getCreator(),
            webtoon.getCreatorId(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            webtoon.getGenre().getKey(),
            webtoon.getPublicationDays().getKey(),
            webtoon.getWebtoonStatus(),
            webtoon.getCutoutImage()
        );
    }
}
