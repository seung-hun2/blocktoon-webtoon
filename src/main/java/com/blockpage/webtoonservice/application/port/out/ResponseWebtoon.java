package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.domain.Webtoon;
import lombok.Getter;

@Getter
public class ResponseWebtoon {

    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private Integer views;
    private Integer interestCount;
    private Integer genreType;
    private Integer publicationDays;

    public ResponseWebtoon(String webtoonTitle, String webtoonThumbnail, String creator, String illustrator, Integer views,
        Integer interestCount, Integer genreType, Integer publicationDays) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
        this.genreType = genreType;
        this.publicationDays = publicationDays;
    }


    public static ResponseWebtoon toResponseFromEntity(WebtoonEntity webtoon) {
        return new ResponseWebtoon(
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            webtoon.getGenreType().getKey(),
            webtoon.getPublicationDays().getKey()
        );
    }

    public static ResponseWebtoon toResponseFromDomain(Webtoon webtoon) {
        return new ResponseWebtoon(
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            webtoon.getGenre().getKey(),
            webtoon.getPublicationDays().getKey()
        );
    }
}
