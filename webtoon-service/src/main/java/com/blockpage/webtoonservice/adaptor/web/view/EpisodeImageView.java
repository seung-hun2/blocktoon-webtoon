package com.blockpage.webtoonservice.adaptor.web.view;

import java.util.List;
import lombok.Getter;

@Getter
public class EpisodeImageView {
    private List<EpisodeImages> images;
    private int commentCount;
    private double rating;
    private String author;
    private String authorWords;
    private String nextEpisodeTitle;
    private String nextEpisodeThumbnail;

    @Getter
    public static class EpisodeImages{
        private String episodeImage;

        public EpisodeImages(String episodeImage) {
            this.episodeImage = episodeImage;
        }
    }

    public EpisodeImageView(List<EpisodeImages> images, int commentCount, double rating, String author, String authorWords,
        String nextEpisodeTitle, String nextEpisodeThumbnail) {
        this.images = images;
        this.commentCount = commentCount;
        this.rating = rating;
        this.author = author;
        this.authorWords = authorWords;
        this.nextEpisodeTitle = nextEpisodeTitle;
        this.nextEpisodeThumbnail = nextEpisodeThumbnail;
    }

}
