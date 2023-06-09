package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EpisodeDetailView {

    private List<EpisodeImageView> images;
    private Long episodeId;
    private Integer commentCount;
    private double rating;
    private String author;
    private String authorWords;
    private String nextEpisodeTitle;
    private String nextEpisodeThumbnail;
    private double nextRating;
    private String nextUploadDate;
    private Integer nextEpisodeBlockPrice;

    public static EpisodeDetailView toViewFromResponse(ResponseEpisodeDetail responseEpisodeDetail) {
        return EpisodeDetailView.builder()
            .images(responseEpisodeDetail.getImages().stream()
                .map(EpisodeImageView::toViewFromResponse)
                .collect(Collectors.toList())
            )
            .episodeId(responseEpisodeDetail.getEpisodeId())
            .commentCount(responseEpisodeDetail.getCommentCount())
            .rating(responseEpisodeDetail.getRating())
            .author(responseEpisodeDetail.getAuthor())
            .authorWords(responseEpisodeDetail.getAuthorWords())
            .nextEpisodeTitle(responseEpisodeDetail.getNextEpisodeTitle())
            .nextEpisodeThumbnail(responseEpisodeDetail.getNextEpisodeThumbnail())
            .nextRating(responseEpisodeDetail.getNextRating())
            .nextUploadDate(responseEpisodeDetail.getNextUploadDate())
            .nextEpisodeBlockPrice(responseEpisodeDetail.getNextEpisodeBlockPrice())
            .build();
    }

}
