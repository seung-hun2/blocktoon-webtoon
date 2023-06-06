package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class WebtoonView {

    private Long webtoonId;
    private String webtoonTitle;
    private String webtoonMainImage;
    private String webtoonThumbnail;
    private String creator;
    private String creatorId;
    private String illustrator;
    private Integer views;
    private Integer interestCount;
    private Integer genre;
    private String webtoonStatus;

    private Integer publicationDays;
    private String webtoonDescription;


    public WebtoonView(Long webtoonId, String webtoonTitle, String webtoonMainImage, String webtoonThumbnail, String creator,
        String creatorId, String illustrator, Integer views, Integer interestCount, Integer genre, String webtoonStatus,
        Integer publicationDays, String webtoonDescription) {
        this.webtoonId = webtoonId;
        this.webtoonTitle = webtoonTitle;
        this.webtoonMainImage = webtoonMainImage;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.creatorId = creatorId;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
        this.genre = genre;
        this.webtoonStatus = webtoonStatus;
        this.publicationDays = publicationDays;
        this.webtoonDescription = webtoonDescription;
    }

    public static WebtoonView toViewFromResponse(ResponseWebtoon webtoon) {
        return new WebtoonView(
            webtoon.getWebtoonId(),
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonMainImage(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
            webtoon.getCreatorId(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            webtoon.getGenreType(),
            webtoon.getWebtoonStatus(),
            webtoon.getPublicationDays(),
            webtoon.getDescription()
        );
    }

}
