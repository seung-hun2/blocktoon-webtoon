package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonRepository extends JpaRepository<WebtoonEntity, Long> {

    List<WebtoonEntity> findAllByGenreTypeAndWebtoonStatus(GenreType genre, WebtoonStatus status);

    List<WebtoonEntity> findByPublicationDaysAndWebtoonStatus(PublicationDays publicationDays, WebtoonStatus status);

    List<WebtoonEntity> findByWebtoonStatusOrderByViews(WebtoonStatus status);

    List<WebtoonEntity> findByCreatorId(Long creatorId);

    List<WebtoonEntity> findByCreatorIdAndWebtoonStatus(Long creatorId, WebtoonStatus webtoonStatus);

    Optional<WebtoonEntity> findByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, Long creatorId, WebtoonStatus status);

    void deleteByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, Long creatorId, WebtoonStatus status);


}
