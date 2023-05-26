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
    private String webtoonThumbnail;
    private List<ResponseEpisode> episodeViewList;

    public WebtoonDescribeView(List<ResponseEpisode> episodeViewList, ResponseWebtoon responseWebtoon) {
        this.webtoonTitle = responseWebtoon.getWebtoonTitle();
        this.creator = responseWebtoon.getCreator();
        this.illustrator = responseWebtoon.getIllustrator();
        this.publicationDays = PublicationDays.findPublicationDaysByKey(responseWebtoon.getPublicationDays());
        this.genre = GenreType.findGenreTypeByKey(responseWebtoon.getGenreType());
        this.webtoonThumbnail = responseWebtoon.getWebtoonThumbnail();
        this.episodeViewList = episodeViewList;
    }
}
