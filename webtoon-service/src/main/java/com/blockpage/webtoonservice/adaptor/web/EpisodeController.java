package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonDescribeView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/episodes")
public class EpisodeController {

    @GetMapping("")
    public ResponseEntity<ApiResponseView> episodeSearch(@RequestParam int webtoonId){
        List<EpisodeView> episodeView = new ArrayList<>();
        WebtoonDescribeView webtoonDescribeViews = null;

        episodeView.add(new EpisodeView("1화","http://","230507",2200));
        episodeView.add(new EpisodeView("2화","http://","230514",100));
        episodeView.add(new EpisodeView("3화","http://","230521",400));
        episodeView.add(new EpisodeView("4화","http://","230528",800));

        webtoonDescribeViews = new WebtoonDescribeView("침대다이부", "백고은",null,
            PublicationDays.MONDAY, GenreType.COMIC,"https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            episodeView);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(webtoonDescribeViews));
    }


}
