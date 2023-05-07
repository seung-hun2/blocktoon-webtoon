package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import lombok.Getter;

@Getter
public class WebtoonView {

    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private int views;
    private int interestCount;

    public WebtoonView(String webtoonTitle, String webtoonThumbnail, String creator, String illustrator, int views, int interestCount) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
    }

    public static WebtoonView toViewFromEntity(WebtoonEntity webtoon) {
        return new WebtoonView(
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount());
    }
}
