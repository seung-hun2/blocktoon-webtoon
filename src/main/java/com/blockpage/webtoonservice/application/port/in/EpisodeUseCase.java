package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.application.port.out.ResponseCreatorEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import java.util.List;
import lombok.Getter;

public interface EpisodeUseCase {

    List<ResponseEpisode> findEpisode(Long webtoonId, String sort);

    List<ResponseCreatorEpisode> findCreatorEpisode(Long webtoonId);

    ResponseEpisodeDetail findEpisodeDetail(Long episodeId, Long webtoonId, Integer episodeNumber);

    @Getter
    class RequestEpisode {

        private String webtoonTitle;
        private String episodeTitle;
        private String uploadDate;
        private String authorWords;
        private String episodeThumbnail;
        private Images episodeImages;

        @Getter
        public class Images {

            private String image;
        }
    }

}
