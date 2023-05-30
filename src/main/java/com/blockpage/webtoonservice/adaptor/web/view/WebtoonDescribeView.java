package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonDescribeView {

    private String webtoonTitle;
    private String creator;
    private String illustrator;
    private String description;
    private String publicationDays;
    private String genre;
    private String webtoonMainImage;
    private Integer views;
    private Integer interestCount;
    private List<ResponseEpisode> episodeViewList;

    public WebtoonDescribeView(List<ResponseEpisode> episodeViewList, ResponseWebtoon responseWebtoon) {
        this.webtoonTitle = responseWebtoon.getWebtoonTitle();
        this.creator = responseWebtoon.getCreator();
        this.illustrator = responseWebtoon.getIllustrator();
        this.description = responseWebtoon.getDescription();
        this.publicationDays = PublicationDays.findPublicationDaysByKey(responseWebtoon.getPublicationDays()).getValue();
        this.genre = GenreType.findGenreTypeByKey(responseWebtoon.getGenreType()).getValue();
        this.webtoonMainImage = responseWebtoon.getWebtoonMainImage();
        this.views = responseWebtoon.getViews();
        this.interestCount = responseWebtoon.getInterestCount();
        this.episodeViewList = episodeViewList;
    }
}
