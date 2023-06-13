package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Long> {

    List<EpisodeEntity> findByEpisodeStatusAndWebtoonIdOrderByEpisodeNumber(WebtoonStatus status, Long webtoonId);

    List<EpisodeEntity> findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(Long webtoonId, WebtoonStatus status);

    List<EpisodeEntity> findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberAsc(Long webtoonId, WebtoonStatus status);

    Optional<EpisodeEntity> findByEpisodeTitleAndEpisodeStatus(String episodeTitle, WebtoonStatus status);

    Optional<EpisodeEntity> findByWebtoonIdAndEpisodeNumberAndEpisodeStatus(Long webtoonId, int episodeNumber, WebtoonStatus status);

    Optional<EpisodeEntity> findByEpisodeTitleAndCreatorIdAndEpisodeStatus(String title, String creatorId, WebtoonStatus status);

    void deleteByEpisodeTitleAndCreatorIdAndEpisodeStatus(String title, String creatorId, WebtoonStatus status);

    List<EpisodeEntity> findByEpisodeStatus(WebtoonStatus status);

    List<EpisodeEntity> findAllByEpisodeStatus(WebtoonStatus status);

    @Modifying(clearAutomatically = true)
    @Query("update EpisodeEntity e set e.episodePrice = 0 where e.uploadDate <= :date")
    void bulkPriceChange(@Param("date") Date date);
}
