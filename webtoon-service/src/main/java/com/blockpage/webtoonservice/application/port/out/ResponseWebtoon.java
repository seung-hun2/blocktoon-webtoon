package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.domain.Webtoon;
import lombok.Getter;

@Getter
public class ResponseWebtoon {

    private String webtoonTitle;
    private String webtoonMainImage;
    private String creator;
    private String illustrator;
    private int views;
    private int interestCount;
    private int genreType;
    private int publicationDays;

    public ResponseWebtoon(String webtoonTitle, String webtoonMainImage, String creator, String illustrator, int views, int interestCount,
        int genreType, int publicationDays) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonMainImage = webtoonMainImage;
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
            webtoon.getWebtoonMainImage(),
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
            webtoon.getWebtoonMainImage(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            webtoon.getGenre().getKey(),
            webtoon.getPublicationDays().getKey()
        );
    }
}
