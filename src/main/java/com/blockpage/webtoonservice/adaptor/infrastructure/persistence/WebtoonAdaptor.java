package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonSpecification;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.out.WebtoonPort;
import com.blockpage.webtoonservice.domain.Webtoon;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebtoonAdaptor implements WebtoonPort {

    private final WebtoonRepository webtoonRepository;

    @Override
    @Transactional(readOnly = true)
    public Webtoon findWebtoon(Long id) {
        Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findById(id);
        return Webtoon.toDomainFromEntity(webtoonEntity.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Webtoon> findAll(String keyword) {

        Specification<WebtoonEntity> spec = Specification.where(WebtoonSpecification.findByCreator(keyword));

        if (keyword != null) {
            spec = spec.or((WebtoonSpecification.containingTitle(keyword)));
        }

        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findAll(spec);
        List<Webtoon> webtoonList;
        webtoonList = webtoonEntityList.stream().map(Webtoon::toDomainFromEntity).toList();

        return webtoonList;
    }

    @Override
    @Transactional
    public void updateViewCount(Long webtoonId, Integer viewCount) {

        WebtoonEntity webtoonEntity = webtoonRepository.findById(webtoonId).get();
        //
        webtoonRepository.updateViewCount(webtoonId, webtoonEntity.getViews()+viewCount);
//        webtoonEntity.updateViewCount(viewCount);
    }

    @Override
    @Transactional
    public void updateInterestCount(Long webtoonId, Integer interestCount) {
        Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findById(webtoonId);
        Integer interests = webtoonEntity.get().getInterestCount();

        webtoonEntity.get().updateInterestCount(interests + interestCount);
    }

    @Override
    @Transactional
    public void updateRating(Long webtoonId, Integer totalScore, Integer participantCount) {
        Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findById(webtoonId);
        Integer total = webtoonEntity.get().getTotalScore();
        Integer participants = webtoonEntity.get().getParticipantCount();
        webtoonEntity.get().updateRating(totalScore + total, participants + participantCount);
        log.info("before total : " + totalScore);
        log.info("after total : " + (total + totalScore));
        log.info("before participantCount : " + participantCount);
        log.info("after participantCount : " + (participants + participantCount));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Webtoon> findMain() {
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findTop10ByOrderByViewsDesc();
        List<Webtoon> webtoonList;

        webtoonList = webtoonEntityList.stream().map(Webtoon::toDomainFromEntity).toList();
        return webtoonList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Webtoon> findTop10Weekdays(String type) {
        List<Webtoon> webtoonList;
        List<WebtoonEntity> webtoonEntityList = webtoonRepository.findTop10ByPublicationDaysAndWebtoonStatusOrderByViewsDesc(
            PublicationDays.findPublicationDaysByKey(Integer.parseInt(type)), WebtoonStatus.PUBLISH);
        webtoonList = webtoonEntityList.stream().map(Webtoon::toDomainFromEntity).toList();
        return webtoonList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Webtoon> findWebtoonByGenre(String type) {

        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findAllByGenreTypeAndWebtoonStatusOrderByViewsDesc(
            GenreType.findGenreTypeByKey(Integer.parseInt(type)), WebtoonStatus.PUBLISH);

        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());
        return webtoonList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Webtoon> findWebtoonByWeekdays(String type) {

        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findByPublicationDaysAndWebtoonStatusOrderByViewsDesc(
            PublicationDays.findPublicationDaysByKey(Integer.parseInt(type)), WebtoonStatus.PUBLISH);

        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());
        return webtoonList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Webtoon> findWebtoonBest() {
        List<WebtoonEntity> webtoonEntityList;
        List<Webtoon> webtoonList;

        webtoonEntityList = webtoonRepository.findByWebtoonStatusOrderByViewsDesc(WebtoonStatus.PUBLISH);

        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());

        return webtoonList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Webtoon> findWebtoonByCreator(String creatorId) {

        List<WebtoonEntity> webtoonEntityList = new ArrayList<>();
        List<Webtoon> webtoonList;

        List<WebtoonEntity> webtoonEntityList1 = webtoonRepository.findByCreatorIdAndWebtoonStatus(creatorId, WebtoonStatus.PUBLISH);
        List<WebtoonEntity> webtoonEntityList2 = webtoonRepository.findByCreatorIdAndWebtoonStatus(creatorId, WebtoonStatus.MODIFICATION_WAITING);
        List<WebtoonEntity> webtoonEntityList3 = webtoonRepository.findByCreatorIdAndWebtoonStatus(creatorId, WebtoonStatus.REMOVE_WAITING);
        webtoonEntityList.addAll(webtoonEntityList1);
        webtoonEntityList.addAll(webtoonEntityList2);
        webtoonEntityList.addAll(webtoonEntityList3);

        webtoonList = webtoonEntityList.stream()
            .map(Webtoon::toDomainFromEntity)
            .collect(Collectors.toList());

        return webtoonList;
    }


}
