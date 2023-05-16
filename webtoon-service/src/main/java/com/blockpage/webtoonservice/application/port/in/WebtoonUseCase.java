package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.List;
import lombok.Getter;

public interface WebtoonUseCase {

    List<WebtoonView> findWebtoonByGenre(String type);

    List<WebtoonView> findWebtoonByWeekdays(String type);

    List<WebtoonView> findWebtoonBest();

    List<WebtoonView> findWebtoonByCreator();

    ResponseWebtoon findWebtoon(Long id);

    @Getter
    public class RequestWebtoon {

        private Long creatorId;
        private String creator;
        private String webtoonTitle;
        private String webtoonDescription;
        private int genre;
        private int publicationDays;
        private String illustrator;


    }
}
