package com.blockpage.webtoonservice.adaptor.web.controller;

import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.MainView;
import com.blockpage.webtoonservice.adaptor.web.view.SearchWebtoonView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import com.blockpage.webtoonservice.application.port.in.WebtoonUseCase;
import com.blockpage.webtoonservice.application.port.in.WebtoonUseCase.RequestType;
import com.blockpage.webtoonservice.application.port.out.ResponseMain;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/webtoon-service/v1/webtoons")
public class WebtoonController {

    private final WebtoonUseCase webtoonUseCase;

    /**
     * @param weekdays 요일별 웹툰 조회
     * @param genre    장르별 웹툰 조회
     */

    @GetMapping("")
    public ResponseEntity<ApiResponseView<List<WebtoonView>>> byGenreOrWeekdays(
        @RequestParam(required = false) String weekdays,
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) String best) {
        List<ResponseWebtoon> responseWebtoonList;

        if (genre != null) {
            responseWebtoonList = webtoonUseCase.findWebtoonByGenre(RequestType.toRequest(weekdays, genre, best));
        } else if (weekdays != null) {
            responseWebtoonList = webtoonUseCase.findWebtoonByWeekdays(RequestType.toRequest(weekdays, genre, best));
        } else {
            return (ResponseEntity<ApiResponseView<List<WebtoonView>>>) ResponseEntity.badRequest();
        }

        List<WebtoonView> webtoonViewList = responseWebtoonList.stream().map(WebtoonView::toViewFromResponse).toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }

    /**
     * 인기순 웹툰 조회
     */

    @GetMapping("/best")
    public ResponseEntity<ApiResponseView<List<WebtoonView>>> byBest() {

        List<ResponseWebtoon> responseWebtoonList = webtoonUseCase.findWebtoonBest();
        List<WebtoonView> webtoonViewList = responseWebtoonList.stream().map(WebtoonView::toViewFromResponse).toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }


    @GetMapping("/creator")
    public ResponseEntity<ApiResponseView<List<WebtoonView>>> byCreator(
        @RequestHeader String memberId) {
        List<ResponseWebtoon> responseWebtoonList = webtoonUseCase.findWebtoonByCreator(memberId);
        List<WebtoonView> webtoonViewList = responseWebtoonList.stream().map(WebtoonView::toViewFromResponse).toList();

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponseView<WebtoonView>> getWebtoon(@RequestParam Long webtoonId) {
        ResponseWebtoon responseWebtoon = webtoonUseCase.findWebtoon(webtoonId);
        WebtoonView webtoonView = WebtoonView.toViewFromResponse(responseWebtoon);

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonView));

    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponseView<List<SearchWebtoonView>>> searchAll(
        @RequestParam String keyword) {

        List<ResponseWebtoon> responseWebtoonList = webtoonUseCase.findAll(keyword);

        List<SearchWebtoonView> webtoonViewList = responseWebtoonList.stream().map(SearchWebtoonView::searchView).toList();

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(webtoonViewList));
    }

    @GetMapping("/main")
    public ResponseEntity<ApiResponseView<List<MainView>>> main(){
        List<ResponseMain> main = webtoonUseCase.findMain();

        List<MainView> mainViewList = main.stream().map(MainView::fromResponse).toList();

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(mainViewList));
    }

}
