package com.blockpage.webtoonservice.application.port.in;

import lombok.Getter;

@Getter
public class RequestWebtoon {

    private String webtoonTitle;
    private String webtoonDescription;
    private String genre;
    private String publicationDays;
    private String illustrator;
    private String webtoonMainImage;
    private String webtoonThumbnail;

}
