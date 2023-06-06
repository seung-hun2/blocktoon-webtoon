package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Page<ImageEntity> findByWebtoonIdAndEpisodeNumber(Long webtoonId, Integer episodeNumber, Pageable pageable);
    List<ImageEntity> findByWebtoonIdAndEpisodeNumber(Long webtoonId, Integer episodeNumber);
    List<ImageEntity> findByEpisodeId(Long episodeId);

}
