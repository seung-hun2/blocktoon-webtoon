package com.blockpage.webtoonservice.application.port.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDemand {

    private String creatorId;
    private String creatorNickname;
    private String webtoonTitle;
    private String webtoonDescription;
    private Integer genre;
    private String webtoonStatus;
    private Integer publicationDays;
    private String illustrator;

    private Long webtoonId;
    private Integer episodeNumber;
    private String episodeTitle;
    private String uploadDate;
    private String authorWords;


}