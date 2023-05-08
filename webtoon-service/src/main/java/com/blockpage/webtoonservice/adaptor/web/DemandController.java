package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.application.port.in.RequestEpisode;
import com.blockpage.webtoonservice.application.port.in.RequestWebtoon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/demands")
public class DemandController {

    @PostMapping("/webtoon/enroll")
    public ResponseEntity enroll(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 등록하는 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("웹툰이 생성되었습니다."));
    }

    @PostMapping("/webtoon/modifying")
    public ResponseEntity modifyWaiting(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 수정요청 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("웹툰 수정 요청되었습니다."));
    }

    @PatchMapping("/webtoon/modifying/admin")
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

    @PostMapping("/webtoon/remove")
    public ResponseEntity removeWaiting(@RequestBody RequestWebtoon requestWebtoon) {
        // 웹툰 수정요청 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("웹툰 삭제요청되었습니다."));
    }

    @PatchMapping("/webtoon/remove/admin")
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


    @PostMapping("/episode/enroll")
    public ResponseEntity episodeEnroll(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 등록 요청 생성 서비스 로직 구현

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 등록 요청이 완료되었습니다."));
    }

    @PatchMapping("/episode/enroll/admin")
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

    @PostMapping("/episode/modify")
    public ResponseEntity episodeModify(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 수정 요청 생성 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 수정 요청이 완료되었습니다."));
    }

    @PatchMapping("/episode/modify/admin")
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

    @PostMapping("/episode/remove")
    public ResponseEntity episodeRemove(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 삭제 요청 생성 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 삭제 요청이 완료되었습니다."));
    }

    @PatchMapping("/episode/remove/admin")
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


}
