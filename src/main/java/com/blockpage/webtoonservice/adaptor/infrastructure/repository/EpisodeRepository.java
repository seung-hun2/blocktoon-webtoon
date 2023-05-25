package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Long> {

    List<EpisodeEntity> findByEpisodeStatus(WebtoonStatus status);

    List<EpisodeEntity> findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(Long webtoonId, WebtoonStatus status);
    List<EpisodeEntity> findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberAsc(Long webtoonId, WebtoonStatus status);

    Optional<EpisodeEntity> findByEpisodeTitleAndEpisodeStatus(String episodeTitle, WebtoonStatus status);

    Optional<EpisodeEntity> findByWebtoonIdAndEpisodeNumberAndEpisodeStatus(Long webtoonId, int episodeNumber, WebtoonStatus status);

    Optional<EpisodeEntity> findByEpisodeTitleAndCreatorIdAndEpisodeStatus(String title, Long creatorId, WebtoonStatus status);

    void deleteByEpisodeTitleAndCreatorIdAndEpisodeStatus(String title, Long creatorId, WebtoonStatus status);

    Page<EpisodeEntity> findByCreatorIdAndEpisodeStatus(Long creatorId, WebtoonStatus status, Pageable pageable);
}
