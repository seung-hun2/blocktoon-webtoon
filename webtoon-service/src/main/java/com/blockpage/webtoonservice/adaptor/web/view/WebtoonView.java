package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import lombok.Getter;

@Getter
public class WebtoonView {

    private String webtoonTitle;
    private String webtoonMainImage;
    private String creator;
    private String illustrator;
    private int views;
    private int interestCount;
    private int genreType;

    public WebtoonView(String webtoonTitle, String webtoonMainImage, String creator, String illustrator, int views, int interestCount,
        int genreType) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonMainImage = webtoonMainImage;
        this.creator = creator;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
        this.genreType = genreType;
    }

    public static WebtoonView toViewFromResponse(ResponseWebtoon webtoon) {
        return new WebtoonView(
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonMainImage(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            webtoon.getGenreType());
    }
}
