package com.blockpage.webtoonservice.adaptor.web.view;

import java.util.List;
import lombok.Getter;

public class DemandEpisodeView {

    private String webtoonTitle;
    private String episodeTitle;
    private String uploadDate;
    private String authorWords;
    private String episodeThumbnail;
    private List<Images> episodeImages;
    private int webtoonStatus;

    @Getter
    public static class Images {

        private String image;

        public Images(String image) {
            this.image = image;
        }
    }

    public DemandEpisodeView(String webtoonTitle, String episodeTitle, String uploadDate, String authorWords, String episodeThumbnail,
        List<Images> episodeImages, int webtoonStatus) {
        this.webtoonTitle = webtoonTitle;
        this.episodeTitle = episodeTitle;
        this.uploadDate = uploadDate;
        this.authorWords = authorWords;
        this.episodeThumbnail = episodeThumbnail;
        this.episodeImages = episodeImages;
        this.webtoonStatus = webtoonStatus;
    }
}
