package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mock/v1/webtoons")
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
            webtoonEntityList.stream()
                .map(w -> WebtoonView.toViewFromEntity(w))
                .collect(Collectors.toList());
        } else if (weekdays != null) {
            webtoonEntityList = webtoonRepository.findByPublicationDaysAndWebtoonStatus(
                PublicationDays.findPublicationDaysByKey(Integer.parseInt(weekdays)), WebtoonStatus.PUBLISH);
            webtoonEntityList.stream()
                .map(w -> WebtoonView.toViewFromEntity(w))
                .collect(Collectors.toList());
        }

        return ResponseEntity.status(HttpStatus.OK)
            .body(webtoonViewList);

    }

    /**
     * 인기순 웹툰 조회
     */

    @GetMapping("/best")
    public ResponseEntity byBest() {
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findByWebtoonStatusOrderByViews(WebtoonStatus.PUBLISH);
        List<WebtoonView> webtoonViewList = new ArrayList<>();

        for (int i = 0; i < webtoonEntityList.size(); i++) {

            webtoonViewList.add(WebtoonView.builder()
                .creator(webtoonEntityList.get(i).getCreator())
                .webtoonTitle(webtoonEntityList.get(i).getWebtoonTitle())
                .webtoonThumbnail(webtoonEntityList.get(i).getWebtoonThumbnail())
                .illustrator(webtoonEntityList.get(i).getIllustrator())
                .views(webtoonEntityList.get(i).getViews())
                .interestCount(webtoonEntityList.get(i).getInterestCount())
                .build());
        }

        return ResponseEntity.status(HttpStatus.OK)
            .body(webtoonViewList);

    }


    /**
     * 작가가 자신의 웹툰 조회
     */
    @GetMapping("/creator")
    public ResponseEntity byCreator() {
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findByCreatorId(1L);
        List<WebtoonView> webtoonViewList = new ArrayList<>();

        for (int i = 0; i < webtoonEntityList.size(); i++) {
            webtoonViewList.add(WebtoonView.builder()
                .creator(webtoonEntityList.get(i).getCreator())
                .webtoonTitle(webtoonEntityList.get(i).getWebtoonTitle())
                .webtoonThumbnail(webtoonEntityList.get(i).getWebtoonThumbnail())
                .illustrator(webtoonEntityList.get(i).getIllustrator())
                .views(webtoonEntityList.get(i).getViews())
                .interestCount(webtoonEntityList.get(i).getInterestCount())
                .build());
        }

        return ResponseEntity.status(HttpStatus.OK)
            .body(webtoonViewList);

    }


}
