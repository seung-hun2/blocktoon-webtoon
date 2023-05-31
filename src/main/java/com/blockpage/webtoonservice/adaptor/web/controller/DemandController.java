package com.blockpage.webtoonservice.adaptor.web.controller;

import com.blockpage.webtoonservice.adaptor.web.view.ApiResponseView;
import com.blockpage.webtoonservice.adaptor.web.view.DemandView;
import com.blockpage.webtoonservice.adaptor.web.view.MessageView;
import com.blockpage.webtoonservice.application.port.DemandDto;
import com.blockpage.webtoonservice.application.port.in.DemandUseCase;
import com.blockpage.webtoonservice.application.port.in.DemandUseCase.DemandQuery;
import com.blockpage.webtoonservice.application.port.in.RequestDemand;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webtoon-service/v1/demands")
public class DemandController {

    private final DemandUseCase demandUseCase;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponseView<MessageView>> postDemand(
        @RequestHeader String email,
        @RequestParam String target,
        @RequestParam String type,
        @ModelAttribute RequestDemand requestDemand,
        @ModelAttribute MultipartFile webtoonMainImage,
        @ModelAttribute MultipartFile webtoonThumbnail,
        @ModelAttribute MultipartFile episodeThumbnail,
        @ModelAttribute List<MultipartFile> episodeImage) throws IOException, ParseException {

        // creatorId Authentication 으로 받아오기 !
        if (episodeThumbnail == null) {
            // webtoon 이 등록 -> query 로 매핑 할 때 달라져야 함
            demandUseCase.postDemand(
                DemandQuery.toQueryFromWebtoon(email, target, type, requestDemand, webtoonMainImage, webtoonThumbnail));
        } else {
            // episode 가 등록 -> query 매핑
            demandUseCase.postDemand(
                DemandQuery.toQueryFromEpisode(email, target, type, requestDemand, episodeThumbnail, episodeImage));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>(new MessageView("요청이 완료 되었습니다.")));
    }

    @PutMapping("")
    public ResponseEntity<ApiResponseView<MessageView>> checkDemand(
        @RequestHeader String email,
        @RequestParam String target,
        @RequestParam String type,
        @RequestParam String whether,
        @RequestParam(required = false) Long webtoonId,
        @RequestParam(required = false) Long episodeId) throws IOException, ParseException {

        if (webtoonId != null) {
            demandUseCase.checkDemand(DemandQuery.toQueryFromId(target, type, whether, email, webtoonId));
        } else if (episodeId != null) {
            demandUseCase.checkDemand(DemandQuery.toQueryFromId(target, type, whether, email, episodeId));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseView<>(new MessageView("실패 하였습니다.")));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseView<>(new MessageView("요청이 완료 되었습니다.")));
    }

    @GetMapping()
    public ResponseEntity<ApiResponseView<List<DemandView>>> getDemand(
        @RequestHeader String email,
        @RequestParam String target,
        @RequestParam String type,
        @RequestParam Integer pageNo) throws IOException {

        List<DemandDto> demandDtoList = demandUseCase.getDemand(DemandQuery.toQueryFromId(target, type, email, pageNo));
        List<DemandView> demandViewList = demandDtoList.stream().map(DemandView::toView).toList();

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseView<>(demandViewList));
    }

}
