package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.List;
import lombok.Getter;

public interface WebtoonUseCase {

    List<ResponseWebtoon> findWebtoonByGenre(String type);

    List<ResponseWebtoon> findWebtoonByWeekdays(String type);

    List<ResponseWebtoon> findWebtoonBest();

    List<ResponseWebtoon> findWebtoonByCreator();

    ResponseWebtoon findWebtoon(Long id);

    List<ResponseWebtoon> findAll(String creator, String illustrator, String title);

    @Getter
    public class RequestWebtoon {

        private Long creatorId;
        private String creator;
        private String webtoonTitle;
        private String webtoonDescription;
        private Integer genre;
        private Integer publicationDays;
        private String illustrator;


    }
}
