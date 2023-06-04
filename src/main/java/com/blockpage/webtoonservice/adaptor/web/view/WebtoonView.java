package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import lombok.Getter;

@Getter
public class WebtoonView {

    private Long webtoonId;
    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private Integer views;
    private Integer interestCount;
    private Integer genreType;
    private String webtoonStatus;

    private Integer publicationDays;
    private String webtoonDescription;

    public WebtoonView(Long webtoonId, String webtoonTitle, String webtoonThumbnail, String creator, String illustrator, Integer views,
        Integer interestCount, Integer genreType, String webtoonStatus, Integer publicationDays, String webtoonDescription) {
        this.webtoonId = webtoonId;
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
        this.genreType = genreType;
        this.webtoonStatus = webtoonStatus;
        this.publicationDays = publicationDays;
        this.webtoonDescription = webtoonDescription;
    }

    public static WebtoonView toViewFromResponse(ResponseWebtoon webtoon) {
        return new WebtoonView(
            webtoon.getWebtoonId(),
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
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
