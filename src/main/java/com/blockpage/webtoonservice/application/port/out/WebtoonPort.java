package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.domain.Webtoon;
import java.util.List;

public interface WebtoonPort {

    List<Webtoon> findWebtoonByGenre(String type);

    List<Webtoon> findWebtoonByWeekdays(String type);

    List<Webtoon> findWebtoonBest();

    List<Webtoon> findWebtoonByCreator();

    Webtoon findWebtoon(Long id);

    List<Webtoon> findAll(String creator, String illustrator, String title);

}
