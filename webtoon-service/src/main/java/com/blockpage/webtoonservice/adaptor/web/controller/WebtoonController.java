package com.blockpage.webtoonservice.adaptor.web.controller;

import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import com.blockpage.webtoonservice.application.port.in.WebtoonUseCase;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/webtoons")
public class WebtoonController {

    private final WebtoonUseCase webtoonUseCase;

    /**
     * @param weekdays 요일별 웹툰 조회
     * @param genre    장르별 웹툰 조회
     */

    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<WebtoonView>>> byGenreOrWeekdays(@RequestParam(required = false) String weekdays,
        @RequestParam(required = false) String genre) {
        List<WebtoonView> webtoonViewList = new ArrayList<>();

        System.out.println("weekdays = " + weekdays);
        if (genre != null) {
            webtoonViewList = webtoonUseCase.findWebtoonByGenre(genre);
        } else if (weekdays != null) {
            webtoonViewList = webtoonUseCase.findWebtoonByWeekdays(weekdays);
        } else {
            return (ResponseEntity<ApiResponseView<List<WebtoonView>>>) ResponseEntity.badRequest();
        }

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }

    /**
     * 인기순 웹툰 조회
     */

    @GetMapping("/best")
    public ResponseEntity<ApiResponseView<List<WebtoonView>>> byBest() {

        List<WebtoonView> webtoonViewList = webtoonUseCase.findWebtoonBest();

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }


    @GetMapping("/creator")
    public ResponseEntity<ApiResponseView<List<WebtoonView>>> byCreator() {
        // Authentication 으로 creatorId 받아와야함.
        List<WebtoonView> webtoonViewList = webtoonUseCase.findWebtoonByCreator();

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }


}
