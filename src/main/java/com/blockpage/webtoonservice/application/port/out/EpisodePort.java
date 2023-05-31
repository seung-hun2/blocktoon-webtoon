package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.domain.Episode;
import java.util.List;

public interface EpisodePort {

    List<Episode> findEpisode(Long webtoonId, String sort);

    List<Episode> findCreatorEpisode(Long webtoonId);

    Episode findEpisodeDetail(Long episodeId, Long webtoonId, Integer episodeNumber);

    void updateRating(Long episodeId, Integer totalScore, Integer participantCount);
}
