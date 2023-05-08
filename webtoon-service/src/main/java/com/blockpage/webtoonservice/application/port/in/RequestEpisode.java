package com.blockpage.webtoonservice.application.port.in;

import lombok.Getter;

@Getter
public class RequestEpisode {
    private String webtoonTitle;
    private String episodeTitle;
    private String uploadDate;
    private String authorWords;
    private String episodeThumbnail;
    private Images episodeImages;

    @Getter
    public class Images{
        private String image;
    }

}
