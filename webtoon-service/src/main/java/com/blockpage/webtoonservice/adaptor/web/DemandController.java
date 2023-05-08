package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.WebtoonDescribeView;
import com.blockpage.webtoonservice.application.port.in.RequestEpisode;
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

    @PostMapping("/enroll")
    public ResponseEntity episodeEnroll(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 등록 요청 생성 서비스 로직 구현

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 등록 요청이 완료되었습니다."));
    }

    @PatchMapping("/enroll/admin")
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

    @PostMapping("/modify")
    public ResponseEntity episodeModify(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 수정 요청 생성 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 수정 요청이 완료되었습니다."));
    }

    @PatchMapping("/modify/admin")
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

    @PostMapping("/remove")
    public ResponseEntity episodeRemove(@RequestBody RequestEpisode requestEpisode) {
        // 에피소드 삭제 요청 생성 서비스 로직 구현
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>("에피소드 삭제 요청이 완료되었습니다."));
    }

    @PatchMapping("/remove/admin")
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
