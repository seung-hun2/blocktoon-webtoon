package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.DemandEpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.DemandEpisodeView.Images;
import com.blockpage.webtoonservice.adaptor.web.view.DemandWebtoonView;
import com.blockpage.webtoonservice.application.port.in.RequestEpisode;
import com.blockpage.webtoonservice.application.port.in.RequestWebtoon;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/demands")
public class DemandController {

    @PostMapping("/webtoons/enroll")
    public ResponseEntity enroll(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 등록하는 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView("웹툰이 생성되었습니다."));
    }

    @PostMapping("/webtoons/modifying")
    public ResponseEntity modifyWaiting(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 수정요청 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView("웹툰 수정 요청되었습니다."));
    }

    @PatchMapping("/webtoons/modifying/admin")
    public ResponseEntity receiveModifying(@RequestBody RequestWebtoon requestWebtoon, @RequestParam String result) {
        // result 값에 따라 승인인지 반려인지 확인해서 넘겨줘야함
        switch (result) {
            case "accept":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView("웹툰 수정요청이 승인되었습니다."));
            case "reject":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView("웹툰 수정요청이 반려되었습니다."));
            default:
                return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/webtoons/remove")
    public ResponseEntity removeWaiting(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 수정요청 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView("웹툰 삭제요청되었습니다."));
    }

    @PatchMapping("/webtoons/remove/admin")
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


    @PostMapping("/episodes/enroll")
    public ResponseEntity episodeEnroll(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 등록 요청 생성 서비스 로직 구현

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 등록 요청이 완료되었습니다."));
    }

    @PatchMapping("/episodes/enroll/admin")
    public ResponseEntity adminEnroll(@RequestBody RequestEpisode requestEpisode, @RequestParam String result) {
        // 에피소드 등록 요청 생성 서비스 로직 구현
        switch (result) {
            case "accept":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("에피소드 등록 요청이 승인되었습니다."));
            case "reject":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("에피소드 등록 요청이 반려되었습니다."));
            default:
                return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/episodes/modify")
    public ResponseEntity episodeModify(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 수정 요청 생성 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 수정 요청이 완료되었습니다."));
    }

    @PatchMapping("/episodes/modify/admin")
    public ResponseEntity adminModify(@RequestBody RequestEpisode requestEpisode, @RequestParam String result) {
        // 에피소드 수정 요청 생성 서비스 로직 구현
        switch (result) {
            case "accept":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("에피소드 수정 요청이 승인되었습니다."));
            case "reject":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("에피소드 수정 요청이 반려되었습니다."));
            default:
                return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/episodes/remove")
    public ResponseEntity episodeRemove(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 삭제 요청 생성 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 삭제 요청이 완료되었습니다."));
    }

    @PatchMapping("/episodes/remove/admin")
    public ResponseEntity adminRemove(@RequestBody RequestEpisode requestEpisode, @RequestParam String result) {
        // 에피소드 삭제 요청 생성 서비스 로직 구현
        switch (result) {
            case "accept":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("에피소드 삭제 요청이 승인되었습니다."));
            case "reject":
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>("에피소드 삭제 요청이 반려되었습니다."));
            default:
                return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/webtoons")
    public ResponseEntity webtoonDemand(@RequestParam String status) {
        List<DemandWebtoonView> demandWebtoonViews = new ArrayList<>();

        demandWebtoonViews.add(new DemandWebtoonView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 0));

        demandWebtoonViews.add(new DemandWebtoonView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 1));

        demandWebtoonViews.add(new DemandWebtoonView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 2));

        demandWebtoonViews.add(new DemandWebtoonView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 3));

        demandWebtoonViews.add(new DemandWebtoonView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 4));

        demandWebtoonViews.add(new DemandWebtoonView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 5));

        demandWebtoonViews.add(new DemandWebtoonView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg",
            "https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg", 6));

        // status 에 따라 수정인지 삭제인지 구분하는 서비스 로직 구현

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(demandWebtoonViews));
    }

    @GetMapping("/episodes")
    public ResponseEntity episodeDemand(@RequestParam String status) {

        List<DemandEpisodeView> demandEpisodeViewList = new ArrayList<>();
        List<Images> imagesList = new ArrayList<>();
        imagesList.add(new Images("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg"));
        imagesList.add(new Images("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg"));
        imagesList.add(new Images("https://user-images.githubusercontent.com/97498405/235885340-d63630ec-85ec-4801-bf73-ac83f96c3bd2.jpg"));

        demandEpisodeViewList.add(new DemandEpisodeView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            imagesList, 0));
        demandEpisodeViewList.add(new DemandEpisodeView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            imagesList, 1));
        demandEpisodeViewList.add(new DemandEpisodeView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            imagesList, 2));
        demandEpisodeViewList.add(new DemandEpisodeView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            imagesList, 3));
        demandEpisodeViewList.add(new DemandEpisodeView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            imagesList, 4));
        demandEpisodeViewList.add(new DemandEpisodeView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            imagesList, 5));
        demandEpisodeViewList.add(new DemandEpisodeView("웹툰 기모링", "재밌는 웹툰입니다아~", "코믹", "금", "만화가",
            imagesList, 6));

        // status 에 따라 수정인지 삭제인지 구분하는 서비스 로직 구현

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(demandEpisodeViewList));
    }


}
