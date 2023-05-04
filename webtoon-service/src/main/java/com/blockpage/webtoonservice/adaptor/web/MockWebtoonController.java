package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.infrastructure.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock/v1/webtoons")
public class MockWebtoonController {

    private final WebtoonRepository webtoonRepository;

    /**
     * @param weekdays 요일별 웹툰 조회
     * @param genre    장르별 웹툰 조회
     */

    @GetMapping("")
    public ResponseEntity byGenreOrWeekdays(@RequestParam(required = false) String weekdays,
                                     @RequestParam(required = false) String genre) {
        List<WebtoonEntity> webtoonGenreList = new ArrayList<>();
        List<WebtoonEntity> webtoonPublicationDaysList = new ArrayList<>();
        List<WebtoonView> webtoonViewList = new ArrayList<>();
        if(genre != null) {
            webtoonGenreList = webtoonRepository.findAllByGenreTypeAndWebtoonStatus(
                    GenreType.findGenreTypeByKey(Integer.parseInt(genre)), WebtoonStatus.PUBLISH);
            for(int i=0;i<webtoonGenreList.size();i++) {
                webtoonViewList.add(WebtoonView.builder()
                        .creator(webtoonGenreList.get(i).getCreator())
                        .webtoonTitle(webtoonGenreList.get(i).getWebtoonTitle())
                        .webtoonThumbnail(webtoonGenreList.get(i).getWebtoonThumbnail())
                        .illustrator(webtoonGenreList.get(i).getIllustrator())
                        .views(webtoonGenreList.get(i).getViews())
                        .interestCount(webtoonGenreList.get(i).getInterestCount())
                        .build());
            }
        } else if (weekdays != null) {
            webtoonPublicationDaysList = webtoonRepository.findByPublicationDaysAndWebtoonStatus(
                    PublicationDays.findPublicationDaysByKey(Integer.parseInt(weekdays)),WebtoonStatus.PUBLISH);
            for(int i=0;i<webtoonPublicationDaysList.size();i++) {
                webtoonViewList.add(WebtoonView.builder()
                        .creator(webtoonPublicationDaysList.get(i).getCreator())
                        .webtoonTitle(webtoonPublicationDaysList.get(i).getWebtoonTitle())
                        .webtoonThumbnail(webtoonPublicationDaysList.get(i).getWebtoonThumbnail())
                        .illustrator(webtoonPublicationDaysList.get(i).getIllustrator())
                        .views(webtoonPublicationDaysList.get(i).getViews())
                        .interestCount(webtoonPublicationDaysList.get(i).getInterestCount())
                        .build());
            }
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(webtoonViewList);

    }

    /**
     * 인기순 웹툰 조회
     */

    @GetMapping("/best")
    public ResponseEntity byBest(){
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findByWebtoonStatusOrderByViews(WebtoonStatus.PUBLISH);
        List<WebtoonView> webtoonViewList = new ArrayList<>();

        for(int i=0;i<webtoonEntityList.size();i++){

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
