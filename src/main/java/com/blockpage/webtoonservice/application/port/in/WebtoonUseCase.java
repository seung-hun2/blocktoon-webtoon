package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.application.port.out.ResponseMain;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

public interface WebtoonUseCase {

    List<ResponseWebtoon> findWebtoonByGenre(RequestType requestType);

    List<ResponseWebtoon> findWebtoonByWeekdays(RequestType requestType);

    List<ResponseWebtoon> findWebtoonBest();

    List<ResponseWebtoon> findWebtoonByCreator(String creatorId);

    ResponseWebtoon findWebtoon(Long id);

    List<ResponseWebtoon> findAll(String keyword);

    List<ResponseMain> findMain();

    @Getter
    @Builder
    public class RequestType {
        String weekdays;
        String genre;
        String best;

        public static RequestType toRequest(String weekdays, String genre, String best){
            return RequestType.builder()
                .weekdays(weekdays)
                .genre(genre)
                .best(best)
                .build();
        }
    }
}
