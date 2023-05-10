package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import java.util.List;

public interface WebtoonUseCase {

    List<WebtoonView> findWebtoonByGenre(String type);

    List<WebtoonView> findWebtoonByWeekdays(String type);

    List<WebtoonView> findWebtoonBest();

    List<WebtoonView> findWebtoonByCreator();
}
