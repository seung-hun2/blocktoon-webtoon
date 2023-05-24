package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.out.WebtoonPort;
import com.blockpage.webtoonservice.domain.Webtoon;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebtoonAdaptor implements WebtoonPort {

    private final WebtoonRepository webtoonRepository;

    @Override
    public Webtoon findWebtoon(Long id) {
        Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findById(id);
        return Webtoon.toDomainFromEntity(webtoonEntity.get());
    }

    @Override
    public List<Webtoon> findWebtoonByGenre(String type) {

        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findAllByGenreTypeAndWebtoonStatus(
            GenreType.findGenreTypeByKey(Integer.parseInt(type)), WebtoonStatus.PUBLISH);

        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());
        return webtoonList;
    }

    @Override
    public List<Webtoon> findWebtoonByWeekdays(String type) {

        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findByPublicationDaysAndWebtoonStatus(
            PublicationDays.findPublicationDaysByKey(Integer.parseInt(type)), WebtoonStatus.PUBLISH);

        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());
        return webtoonList;
    }

    @Override
    public List<Webtoon> findWebtoonBest() {
        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findByWebtoonStatusOrderByViews(WebtoonStatus.PUBLISH);

        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());

        return webtoonList;
    }

    @Override
    public List<Webtoon> findWebtoonByCreator() {

        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findByCreatorId(1L);
        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());

        return webtoonList;
    }


}
