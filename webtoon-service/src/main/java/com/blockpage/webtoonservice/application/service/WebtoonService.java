package com.blockpage.webtoonservice.application.service;

import com.blockpage.webtoonservice.adaptor.web.view.WebtoonView;
import com.blockpage.webtoonservice.application.port.in.WebtoonUseCase;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import com.blockpage.webtoonservice.application.port.out.WebtoonPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebtoonService implements WebtoonUseCase {

    private final WebtoonPort webtoonPort;

    @Override
    public List<WebtoonView> findWebtoonByGenre(String type) {
        List<WebtoonView> webtoonViewList;
        List<ResponseWebtoon> responseWebtoonList = webtoonPort.findWebtoonByGenre(type);
        webtoonViewList = responseWebtoonList.stream().map(WebtoonView::toViewFromResponse).collect(Collectors.toList());

        return webtoonViewList;
    }

    @Override
    public List<WebtoonView> findWebtoonByWeekdays(String type) {
        List<WebtoonView> webtoonViewList;
        List<ResponseWebtoon> responseWebtoonList = webtoonPort.findWebtoonByWeekdays(type);
        webtoonViewList = responseWebtoonList.stream().map(WebtoonView::toViewFromResponse).collect(Collectors.toList());

        return webtoonViewList;
    }

    @Override
    public List<WebtoonView> findWebtoonBest() {
        List<WebtoonView> webtoonViewList;
        List<ResponseWebtoon> responseWebtoonList = webtoonPort.findWebtoonBest();
        webtoonViewList = responseWebtoonList.stream().map(WebtoonView::toViewFromResponse).collect(Collectors.toList());
        return webtoonViewList;
    }

    @Override
    public List<WebtoonView> findWebtoonByCreator() {
        List<WebtoonView> webtoonViewList;
        List<ResponseWebtoon> responseWebtoonList = webtoonPort.findWebtoonByCreator();
        webtoonViewList = responseWebtoonList.stream().map(WebtoonView::toViewFromResponse).collect(Collectors.toList());

        return webtoonViewList;
    }
}
