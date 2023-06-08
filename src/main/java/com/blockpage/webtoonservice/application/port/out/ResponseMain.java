package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.domain.Webtoon;
import lombok.Builder;
import lombok.Getter;
import org.aspectj.lang.annotation.control.CodeGenerationHint;

@Getter
@Builder
public class ResponseMain {

    private Long webtoonId;
    private String cutoutImage;
    private String webtoonTitle;
    private String genre;

    public static ResponseMain fromDomain(Webtoon webtoon){
        return ResponseMain.builder()
            .webtoonId(webtoon.getWebtoonId())
            .cutoutImage(webtoon.getCutoutImage())
            .webtoonTitle(webtoon.getWebtoonTitle())
            .genre(webtoon.getGenre().getValue())
            .build();
    }

}
