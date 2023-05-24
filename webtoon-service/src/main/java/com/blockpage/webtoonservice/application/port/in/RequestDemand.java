package com.blockpage.webtoonservice.application.port.in;

import lombok.Getter;

@Getter
public class RequestDemand {

    private Long creatorId;
    private String creator;
    private String webtoonTitle;
    private String webtoonDescription;
    private Integer genre;
    private Integer publicationDays;
    private String illustrator;

    private Long webtoonId;
    private Integer episodeNumber;
    private String episodeTitle;
    private String uploadDate;
    private String authorWords;


}