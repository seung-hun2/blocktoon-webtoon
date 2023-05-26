package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonSpecification;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.out.WebtoonPort;
import com.blockpage.webtoonservice.domain.Webtoon;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebtoonAdaptor implements WebtoonPort {

    private final WebtoonRepository webtoonRepository;

    @Override
    public Webtoon findWebtoon(Long id) {
        Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findById(id);
        System.out.println("webtoonEntity.get().getWebtoonThumbnail() = " + webtoonEntity.get().getWebtoonThumbnail());
        return Webtoon.toDomainFromEntity(webtoonEntity.get());
    }

    @Override
    public List<Webtoon> findAll(String creator, String illustrator, String webtoonTitle) {

        Specification<WebtoonEntity> spec = (root, query, criteriaBuilder) -> null;

        if (creator != null) {
            spec = spec.and(WebtoonSpecification.equalCreator(creator));
        }
        if (illustrator != null) {
            spec = spec.and(WebtoonSpecification.equalIllustrator(illustrator));
        }
        if (webtoonTitle != null) {
            spec = spec.and(WebtoonSpecification.equalTitle(webtoonTitle));
        }
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findAll(spec);

        List<Webtoon> webtoonList;
        webtoonList = webtoonEntityList.stream().map(Webtoon::toDomainFromEntity).toList();

        return webtoonList;
    }

    @Override
    public List<Webtoon> findWebtoonByGenre(String type) {

        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findAllByGenreTypeAndWebtoonStatusOrderByViews(
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

        webtoonEntityList = webtoonRepository.findByPublicationDaysAndWebtoonStatusOrderByViews(
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
    public List<Webtoon> findWebtoonByCreator(String creatorId) {

        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findByCreatorId(creatorId);
        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());

        return webtoonList;
    }


}
