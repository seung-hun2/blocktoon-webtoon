package com.blockpage.webtoonservice.adaptor.web;

import com.blockpage.webtoonservice.adaptor.infrastructure.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mock/v1/webtoons")
public class MockWebtoonController {

    private final WebtoonRepository webtoonRepository;

    @GetMapping("/{genre}")
    public ResponseEntity byGenre(@PathVariable int genre){

        List<WebtoonEntity> webtoonEntityList =  webtoonRepository.findByGenreTypeAndWebtoonStatus(
                String.valueOf(GenreType.findGenreTypeByKey(genre)),1);
        List<WebtoonView> webtoonViewList = null;

        for(int i=0;i<webtoonEntityList.size();i++){
            webtoonViewList.get(i).builder()
                    .creator(webtoonEntityList.get(i).getCreator())
                    .webtoonTitle(webtoonEntityList.get(i).getWebtoonTitle())
                    .webtoonThumbnail(webtoonEntityList.get(i).getWebtoonThumbnail())
                    .illustrator(webtoonEntityList.get(i).getIllustrator())
                    .views(webtoonEntityList.get(i).getViews())
                    .interestCount(webtoonEntityList.get(i).getInterestCount())
                    .build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(webtoonViewList);
    }


}
