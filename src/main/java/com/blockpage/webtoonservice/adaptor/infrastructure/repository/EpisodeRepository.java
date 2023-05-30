package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, Long> {

    List<EpisodeEntity> findByEpisodeStatusAndWebtoonIdOrderByEpisodeNumber(WebtoonStatus status, Long webtoonId);

    List<EpisodeEntity> findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberDesc(Long webtoonId, WebtoonStatus status);
    List<EpisodeEntity> findByWebtoonIdAndEpisodeStatusOrderByEpisodeNumberAsc(Long webtoonId, WebtoonStatus status);

    Optional<EpisodeEntity> findByEpisodeTitleAndEpisodeStatus(String episodeTitle, WebtoonStatus status);

    Optional<EpisodeEntity> findByWebtoonIdAndEpisodeNumberAndEpisodeStatus(Long webtoonId, int episodeNumber, WebtoonStatus status);

    Optional<EpisodeEntity> findByEpisodeTitleAndCreatorIdAndEpisodeStatus(String title, String creatorId, WebtoonStatus status);

    void deleteByEpisodeTitleAndCreatorIdAndEpisodeStatus(String title, String creatorId, WebtoonStatus status);

    Page<EpisodeEntity> findByCreatorIdAndEpisodeStatus(String creatorId, WebtoonStatus status, Pageable pageable);
}
