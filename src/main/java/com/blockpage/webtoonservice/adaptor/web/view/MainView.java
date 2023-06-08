package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseMain;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainView {
    private Long webtoonId;
    private String cutoutImage;
    private String webtoonTitle;
    private String genre;

    public static MainView fromResponse(ResponseMain responseMain){
        return MainView.builder()
            .webtoonId(responseMain.getWebtoonId())
            .cutoutImage(responseMain.getCutoutImage())
            .webtoonTitle(responseMain.getWebtoonTitle())
            .genre(responseMain.getGenre())
            .build();
    }
}
