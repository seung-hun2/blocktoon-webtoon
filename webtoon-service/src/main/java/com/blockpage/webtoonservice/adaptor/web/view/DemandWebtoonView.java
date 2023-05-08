package com.blockpage.webtoonservice.adaptor.web.view;

import lombok.Getter;

@Getter
public class DemandWebtoonView {

    private String webtoonTitle;
    private String webtoonDescription;
    private String genre;
    private String publicationDays;
    private String illustrator;
    private String webtoonMainImage;
    private String webtoonThumbnail;
    private int episodeStatus;

    public DemandWebtoonView(String webtoonTitle, String webtoonDescription, String genre, String publicationDays, String illustrator,
        String webtoonMainImage, String webtoonThumbnail, int episodeStatus) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonDescription = webtoonDescription;
        this.genre = genre;
        this.publicationDays = publicationDays;
        this.illustrator = illustrator;
        this.webtoonMainImage = webtoonMainImage;
        this.webtoonThumbnail = webtoonThumbnail;
        this.episodeStatus = episodeStatus;
    }
}
