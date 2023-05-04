package com.blockpage.webtoonservice.adaptor.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebtoonRepository extends JpaRepository<WebtoonEntity, Long> {

    List<WebtoonEntity> findAllByGenreTypeAndWebtoonStatus(GenreType genre, WebtoonStatus status);

    List<WebtoonEntity> findByPublicationDaysAndWebtoonStatus(PublicationDays publicationDays, WebtoonStatus status);

    List<WebtoonEntity> findByWebtoonStatusOrderByViews(WebtoonStatus status);

    List<WebtoonEntity> findByCreatorId(Long creatorId);
}
