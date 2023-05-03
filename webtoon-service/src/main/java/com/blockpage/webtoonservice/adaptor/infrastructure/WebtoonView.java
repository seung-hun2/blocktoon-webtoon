package com.blockpage.webtoonservice.adaptor.infrastructure;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebtoonView {

    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private int views;
    private int interestCount;

}
