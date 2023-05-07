package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import com.blockpage.webtoonservice.application.port.in.RequestWebtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/webtoons")
public class WebtoonController {

    private final WebtoonRepository webtoonRepository;

    @PostMapping()
    public ResponseEntity enroll(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 등록하는 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("웹툰이 생성되었습니다."));
    }

    @PostMapping("/modifying")
    public ResponseEntity modifyWaiting(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 수정요청 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("웹툰 수정 요청되었습니다."));
    }

    @PatchMapping("/modifying/admin")
    public ResponseEntity receiveModifying(@RequestBody RequestWebtoon requestWebtoon, @RequestParam String result) {
        // result 값에 따라 승인인지 반려인지 확인해서 넘겨줘야함
        switch (result) {
            case "accept":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("웹툰 수정요청이 승인되었습니다."));
            case "reject":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("웹툰 수정요청이 반려되었습니다."));
            default:
                return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity removeWaiting(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 수정요청 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("웹툰 삭제요청되었습니다."));
    }

    @PatchMapping("/remove/admin")
    public ResponseEntity receiveRemoving(@RequestBody RequestWebtoon requestWebtoon, @RequestParam String result) {
        // result 값에 따라 승인인지 반려인지 확인해서 넘겨줘야함
        switch (result) {
            case "accept":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("웹툰 삭제요청이 승인되었습니다."));
            case "reject":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("웹툰 삭제요청이 반려되었습니다."));
            default:
                return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }


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
