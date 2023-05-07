package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
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
    private List<EpisodeView> episodeViewList;

    public WebtoonDescribeView(String webtoonTitle, String creator, String illustrator, PublicationDays publicationDays, GenreType genre,
        String webtoonMainImage, List<EpisodeView> episodeViewList) {
        this.webtoonTitle = webtoonTitle;
        this.creator = creator;
        this.illustrator = illustrator;
        this.publicationDays = publicationDays;
        this.genre = genre;
        this.webtoonMainImage = webtoonMainImage;
        this.episodeViewList = episodeViewList;
    }
}
