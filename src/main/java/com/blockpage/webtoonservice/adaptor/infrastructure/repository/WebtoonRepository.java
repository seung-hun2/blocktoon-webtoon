package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WebtoonRepository extends JpaRepository<WebtoonEntity, Long>, JpaSpecificationExecutor<WebtoonEntity> {

    List<WebtoonEntity> findAllByGenreTypeAndWebtoonStatusOrderByViewsDesc(GenreType genre, WebtoonStatus status);

    List<WebtoonEntity> findByPublicationDaysAndWebtoonStatusOrderByViewsDesc(PublicationDays publicationDays, WebtoonStatus status);

    List<WebtoonEntity> findByWebtoonStatusOrderByViewsDesc(WebtoonStatus status);

    List<WebtoonEntity> findAllByWebtoonStatus(WebtoonStatus status);

    List<WebtoonEntity> findByCreatorIdAndWebtoonStatus(String creatorId, WebtoonStatus status);

    Page<WebtoonEntity> findByWebtoonStatus(WebtoonStatus webtoonStatus, Pageable pageable);

    Optional<WebtoonEntity> findByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, String creatorId, WebtoonStatus status);

    void deleteByWebtoonTitleAndCreatorIdAndWebtoonStatus(String title, String creatorId, WebtoonStatus status);

    List<WebtoonEntity> findTop10ByOrderByViewsDesc();
    List<WebtoonEntity> findTop10ByPublicationDaysAndWebtoonStatusOrderByViewsDesc(PublicationDays publicationDays, WebtoonStatus webtoonStatus);


    @Modifying
    @Query(value = "UPDATE webtoon w set w.views = :viewCount where w.id = :webtoonId", nativeQuery = true)
    void updateViewCount(Long webtoonId, Integer viewCount);

}
