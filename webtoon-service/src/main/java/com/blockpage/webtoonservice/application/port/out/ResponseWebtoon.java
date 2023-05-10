package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import lombok.Getter;

@Getter
public class ResponseWebtoon {

    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private int views;
    private int interestCount;

    public ResponseWebtoon(String webtoonTitle, String webtoonThumbnail, String creator, String illustrator, int views, int interestCount) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
    }

    public static ResponseWebtoon toResponseFromEntity(WebtoonEntity webtoon) {
        return new ResponseWebtoon(
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount());
    }
}
