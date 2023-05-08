package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    private final WebtoonRepository webtoonRepository;


    /**
     * @param weekdays 요일별 웹툰 조회
     * @param genre    장르별 웹툰 조회
     */

    @GetMapping("")
    public ResponseEntity byGenreOrWeekdays(@RequestParam(required = false) String weekdays,
        @RequestParam(required = false) String genre) {
        List<WebtoonEntity> webtoonEntityList = new ArrayList<>();
        List<WebtoonView> webtoonViewList = new ArrayList<>();

        if (genre != null) {
            webtoonEntityList = webtoonRepository.findAllByGenreTypeAndWebtoonStatus(
                GenreType.findGenreTypeByKey(Integer.parseInt(genre)), WebtoonStatus.PUBLISH);
            webtoonViewList = webtoonEntityList.stream()
                .map(w -> WebtoonView.toViewFromEntity(w))
                .collect(Collectors.toList());
        } else if (weekdays != null) {
            webtoonEntityList = webtoonRepository.findByPublicationDaysAndWebtoonStatus(
                PublicationDays.findPublicationDaysByKey(Integer.parseInt(weekdays)), WebtoonStatus.PUBLISH);
            webtoonViewList = webtoonEntityList.stream()
                .map(w -> WebtoonView.toViewFromEntity(w))
                .collect(Collectors.toList());
        }

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }

    /**
     * 인기순 웹툰 조회
     */

    @GetMapping("/best")
    public ResponseEntity byBest() {
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findByWebtoonStatusOrderByViews(WebtoonStatus.PUBLISH);
        List<WebtoonView> webtoonViewList = new ArrayList<>();
        webtoonViewList = webtoonEntityList.stream()
            .map(w -> WebtoonView.toViewFromEntity(w))
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }


    /**
     * 작가가 자신의 웹툰 조회
     */
    @GetMapping("/creator")
    public ResponseEntity byCreator() {
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findByCreatorId(1L);
        List<WebtoonView> webtoonViewList = new ArrayList<>();
        webtoonViewList = webtoonEntityList.stream()
            .map(w -> WebtoonView.toViewFromEntity(w))
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponseView<>(webtoonViewList));

    }


}
