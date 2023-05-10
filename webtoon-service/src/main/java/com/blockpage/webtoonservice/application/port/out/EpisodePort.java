package com.blockpage.webtoonservice.application.port.out;

import java.util.List;

public interface EpisodePort {

    List<ResponseEpisode> findEpisode(Long webtoonId);

    List<ResponseCreatorEpisode> findCreatorEpisode(Long webtoonId);

    ResponseEpisodeDetail findEpisodeDetail(Long episodeId);
}
