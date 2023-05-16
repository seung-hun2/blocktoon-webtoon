package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.domain.Webtoon;
import java.util.List;

public interface WebtoonPort {

    List<ResponseWebtoon> findWebtoonByGenre(String type);

    List<ResponseWebtoon> findWebtoonByWeekdays(String type);

    List<ResponseWebtoon> findWebtoonBest();

    List<ResponseWebtoon> findWebtoonByCreator();

    Webtoon findWebtoon(Long id);

}
