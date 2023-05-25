package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.google.api.gax.paging.Page;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WebtoonRepository extends JpaRepository<WebtoonEntity, Long>, JpaSpecificationExecutor<WebtoonEntity> {

//    Page<WebtoonEntity> findAll(Specification<WebtoonEntity> spec, Pageable pageable);
    List<WebtoonEntity> findAllByGenreTypeAndWebtoonStatus(GenreType genre, WebtoonStatus status);

    List<WebtoonEntity> findByPublicationDaysAndWebtoonStatus(PublicationDays publicationDays, WebtoonStatus status);

    List<WebtoonEntity> findByWebtoonStatusOrderByViews(WebtoonStatus status);

    List<WebtoonEntity> findByCreatorId(Long creatorId);

    List<WebtoonEntity> findByCreatorIdAndWebtoonStatus(Long creatorId, WebtoonStatus webtoonStatus);

    Optional<WebtoonEntity> findByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, Long creatorId, WebtoonStatus status);

    void deleteByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, Long creatorId, WebtoonStatus status);


}
