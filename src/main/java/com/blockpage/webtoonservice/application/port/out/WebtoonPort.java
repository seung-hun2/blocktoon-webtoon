package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.domain.Webtoon;
import java.util.List;

public interface WebtoonPort {

    List<Webtoon> findWebtoonByGenre(String type);

    List<Webtoon> findWebtoonByWeekdays(String type);

    List<Webtoon> findWebtoonBest();

    List<Webtoon> findWebtoonByCreator(String creatorId);

    Webtoon findWebtoon(Long id);

    List<Webtoon> findAll(String keyword);

    void updateViewCount(Long webtoonId, Integer viewCount);

    void updateInterestCount(Long webtoonId, Integer interestCount);

    void updateRating(Long webtoonId, Integer totalScore, Integer participantCount);

}
