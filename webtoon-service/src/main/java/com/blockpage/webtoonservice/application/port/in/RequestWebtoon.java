package com.blockpage.webtoonservice.application.port.in;

import lombok.Getter;

@Getter
public class RequestWebtoon {

    private Long creatorId;
    private String creator;
    private String webtoonTitle;
    private String webtoonDescription;
    private int genre;
    private int publicationDays;
    private String illustrator;


}
