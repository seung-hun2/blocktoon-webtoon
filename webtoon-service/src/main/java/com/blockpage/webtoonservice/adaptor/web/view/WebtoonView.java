package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebtoonView {

    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private int views;
    private int interestCount;

    public static WebtoonView toViewFromEntity(WebtoonEntity webtoon) {
        return WebtoonView.builder()
            .creator(webtoon.getCreator())
            .views(webtoon.getViews())
            .interestCount(webtoon.getInterestCount())
            .webtoonThumbnail(webtoon.getWebtoonThumbnail())
            .webtoonTitle(webtoon.getWebtoonTitle())
            .illustrator(webtoon.getIllustrator())
            .build();
    }
}
