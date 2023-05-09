package com.blockpage.webtoonservice.domain;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class Webtoon {

    private String webtoonTitle;
    private String webtoonDescription;
    private Long creatorId;
    private String creator;
    private String illustrator;
    private int publicationDays;
    private MultipartFile webtoonMainImage;
    private MultipartFile webtoonThumbnail;
    private int genre;
    private int interestCount;


}
