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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
        Integer views = webtoonRepository.findById(webtoonId).get().getViews();
        webtoonRepository.findById(webtoonId).get().updateViewCount(views + viewCount);
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
