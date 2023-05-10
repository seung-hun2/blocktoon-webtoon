package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import com.blockpage.webtoonservice.application.port.out.WebtoonPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebtoonAdaptor implements WebtoonPort {

    private final WebtoonRepository webtoonRepository;

    @Override
    public List<ResponseWebtoon> findWebtoonByGenre(String type) {

        List<WebtoonEntity> webtoonEntityList;
        List<ResponseWebtoon> responseWebtoonList;

        webtoonEntityList = webtoonRepository.findAllByGenreTypeAndWebtoonStatus(
            GenreType.findGenreTypeByKey(Integer.parseInt(type)), WebtoonStatus.PUBLISH);

        responseWebtoonList = webtoonEntityList.stream()
            .map(ResponseWebtoon::toResponseFromEntity)
            .collect(Collectors.toList());
        return responseWebtoonList;
    }

    @Override
    public List<ResponseWebtoon> findWebtoonByWeekdays(String type) {

        List<WebtoonEntity> webtoonEntityList;
        List<ResponseWebtoon> responseWebtoonList;

        webtoonEntityList = webtoonRepository.findByPublicationDaysAndWebtoonStatus(
            PublicationDays.findPublicationDaysByKey(Integer.parseInt(type)), WebtoonStatus.PUBLISH);

        responseWebtoonList = webtoonEntityList.stream()
            .map(ResponseWebtoon::toResponseFromEntity)
            .collect(Collectors.toList());
        return responseWebtoonList;
    }

    @Override
    public List<ResponseWebtoon> findWebtoonBest() {
        List<WebtoonEntity> webtoonEntityList;
        List<ResponseWebtoon> responseWebtoonList;

        webtoonEntityList = webtoonRepository.findByWebtoonStatusOrderByViews(WebtoonStatus.PUBLISH);

        responseWebtoonList = webtoonEntityList.stream()
            .map(ResponseWebtoon::toResponseFromEntity)
            .collect(Collectors.toList());

        return responseWebtoonList;
    }

    @Override
    public List<ResponseWebtoon> findWebtoonByCreator() {

        List<WebtoonEntity> webtoonEntityList;
        List<ResponseWebtoon> responseWebtoonList;

        webtoonEntityList = webtoonRepository.findByCreatorId(1L);
        responseWebtoonList = webtoonEntityList.stream()
            .map(ResponseWebtoon::toResponseFromEntity)
            .collect(Collectors.toList());

        return responseWebtoonList;
    }
}
