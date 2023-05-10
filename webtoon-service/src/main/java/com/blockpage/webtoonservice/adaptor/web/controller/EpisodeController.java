package com.blockpage.webtoonservice.adaptor.web.controller;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.CreatorEpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeDetailView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeImageView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonDescribeView;
import com.blockpage.webtoonservice.application.port.in.EpisodeUseCase;
import java.sql.Date;
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
@RequestMapping("/v1/episodes")
public class EpisodeController {

    private final EpisodeUseCase episodeUseCase;

    @GetMapping("")
    public ResponseEntity<ApiResponseView> episodeSearch(@RequestParam Long webtoonId) {

        /**
         *  헥사고날 아키텍쳐 적용
         *
         *  List<EpisodeView> episodeViews = new ArrayList<>();
         *  episodeViews = episodeUseCase.findEpisode(webtoonId);
         *  return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView(episodeViews));
         */

        List<EpisodeView> episodeView = new ArrayList<>();
        WebtoonDescribeView webtoonDescribeViews;

        episodeView.add(new EpisodeView("1화", "http://", Date.valueOf("230507"), 2200));
        episodeView.add(new EpisodeView("2화", "http://", Date.valueOf("230514"), 100));
        episodeView.add(new EpisodeView("3화", "http://", Date.valueOf("230521"), 400));
        episodeView.add(new EpisodeView("4화", "http://", Date.valueOf("230528"), 800));

        webtoonDescribeViews = new WebtoonDescribeView("침대다이부", "백고은", null,
            PublicationDays.MONDAY, GenreType.COMIC,
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            episodeView);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView(webtoonDescribeViews));
    }

    @GetMapping("/creator")
    public ResponseEntity<ApiResponseView> myWebtoonSearch(@RequestParam Long memberId, @RequestParam Long webtoonId) {

        /**
         *  헥사고날 아키텍쳐 적용
         *
         *  List<CreatorEpisodeView> creatorEpisodeViewList = new ArrayList<>();
         *  creatorEpisodeViewList = episodeUseCase.findCreatorEpisode(webtoonId);
         *  return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView(creatorEpisodeViewList));
         */

        List<CreatorEpisodeView> creatorEpisodeViews = new ArrayList<>();

        creatorEpisodeViews.add(new CreatorEpisodeView("1화다", 1,
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 2200, 1000,
            Date.valueOf("2023-05-01")));
        creatorEpisodeViews.add(new CreatorEpisodeView("2화다", 2,
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 100, 20,
            Date.valueOf("2023-05-08")));
        creatorEpisodeViews.add(new CreatorEpisodeView("3화다", 3,
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 100, 20,
            Date.valueOf("2023-05-15")));
        creatorEpisodeViews.add(new CreatorEpisodeView("4화다", 4,
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 100, 20,
            Date.valueOf("2023-05-22")));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView(creatorEpisodeViews));
    }

    @GetMapping("/view")
    public ResponseEntity<ApiResponseView> webtoonEpisode(@RequestParam Long episodeId) {

        /**
         *  헥사고날 아키텍쳐 적용
         *
         *  EpisodeDetailView episodeDetailViews = episodeUseCase.findEpisodeDetail(episodeId);
         *  return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView(episodeDetailViews));
         */

        List<EpisodeImageView> episodeImagesList = new ArrayList<>();
        episodeImagesList.add(
            new EpisodeImageView("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg"));
        episodeImagesList.add(
            new EpisodeImageView("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg"));
        episodeImagesList.add(
            new EpisodeImageView("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg"));

        EpisodeDetailView episodeDetailView = new EpisodeDetailView(episodeImagesList, 1L, 9, 9.9, "김태근", "명세 똑바로 해라", "이게 명세다 2화",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg");

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView(episodeDetailView));


    }


}
