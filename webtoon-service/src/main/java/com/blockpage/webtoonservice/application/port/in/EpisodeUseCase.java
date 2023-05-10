package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.adaptor.web.view.CreatorEpisodeView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeDetailView;
import com.blockpage.webtoonservice.adaptor.web.view.EpisodeView;
import java.util.List;

public interface EpisodeUseCase {

    List<EpisodeView> findEpisode(Long webtoonId);

    List<CreatorEpisodeView> findCreatorEpisode(Long webtoonId);

    EpisodeDetailView findEpisodeDetail(Long episodeId);

}
