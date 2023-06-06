package com.blockpage.webtoonservice.adaptor.web.controller;

import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView.Meta;
import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView.Meta.Sort;
import com.blockpage.webtoonservice.adaptor.web.view.CreatorEpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeDetailView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonDescribeView;
import com.blockpage.webtoonservice.application.port.in.EpisodeUseCase;
import com.blockpage.webtoonservice.application.port.in.WebtoonUseCase;
import com.blockpage.webtoonservice.application.port.out.ResponseCreatorEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeContent;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webtoon-service/v1/episodes")
@Slf4j
public class EpisodeController {

    private final EpisodeUseCase episodeUseCase;
    private final WebtoonUseCase webtoonUseCase;

    @GetMapping
    public ResponseEntity<ApiResponseView<WebtoonDescribeView>> episodeSearch(@RequestParam Long webtoonId,
        @RequestParam(required = false, defaultValue = "DESC") String sort) {

        ResponseWebtoon responseWebtoon = webtoonUseCase.findWebtoon(webtoonId);
        List<ResponseEpisode> responseEpisodeList = episodeUseCase.findEpisode(webtoonId, sort);

        WebtoonDescribeView webtoonDescribeView = new WebtoonDescribeView(responseEpisodeList, responseWebtoon);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(webtoonDescribeView, new Meta(sort, null)));
    }

    @GetMapping("/creator")
    public ResponseEntity<ApiResponseView<List<CreatorEpisodeView>>> myWebtoonSearch(
        @RequestParam Long webtoonId) {

        List<ResponseCreatorEpisode> responseCreatorEpisodeList = episodeUseCase.findCreatorEpisode(webtoonId);
        List<CreatorEpisodeView> creatorEpisodeViewList = responseCreatorEpisodeList.stream().map(CreatorEpisodeView::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(creatorEpisodeViewList));
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponseView<EpisodeView>> getEpisode(@RequestParam Long episodeId, @RequestParam Long webtoonId,
        @RequestParam Integer episodeNumber){

        ResponseEpisodeContent responseEpisodeContent = episodeUseCase.findEpisodeContent(episodeId, webtoonId, episodeNumber);
        EpisodeView episodeView = EpisodeView.toViewFromResponse(responseEpisodeContent);
        //EPISODE VIEW 만들어줘야함

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(episodeView));
    }

    @GetMapping("/view")
    public ResponseEntity<ApiResponseView<EpisodeDetailView>> webtoonEpisode(@RequestParam Long episodeId, @RequestParam Long webtoonId,
        @RequestParam Integer episodeNumber) {

        ResponseEpisodeDetail responseEpisodeDetail = episodeUseCase.findEpisodeDetail(episodeId, webtoonId, episodeNumber);
        EpisodeDetailView episodeDetailView = EpisodeDetailView.toViewFromResponse(responseEpisodeDetail);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(episodeDetailView));

    }

}
