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
    private PublicationDays publicationDays;
    private GenreType genre;
    private String webtoonMainImage;
    private List<ResponseEpisode> episodeViewList;

    public WebtoonDescribeView(String webtoonTitle, String creator, String illustrator, PublicationDays publicationDays, GenreType genre,
        String webtoonMainImage, List<ResponseEpisode> episodeViewList) {
        this.webtoonTitle = webtoonTitle;
        this.creator = creator;
        this.illustrator = illustrator;
        this.publicationDays = publicationDays;
        this.genre = genre;
        this.webtoonMainImage = webtoonMainImage;
        this.episodeViewList = episodeViewList;
    }

    public WebtoonDescribeView(List<ResponseEpisode> episodeViewList, ResponseWebtoon responseWebtoon) {
        this.webtoonTitle = responseWebtoon.getWebtoonTitle();
        this.creator = responseWebtoon.getCreator();
        this.illustrator = responseWebtoon.getIllustrator();
        this.publicationDays = PublicationDays.findPublicationDaysByKey(responseWebtoon.getPublicationDays());
        this.genre = GenreType.findGenreTypeByKey(responseWebtoon.getGenreType());
        this.webtoonMainImage = responseWebtoon.getWebtoonMainImage();
        this.episodeViewList = episodeViewList;
    }
}
