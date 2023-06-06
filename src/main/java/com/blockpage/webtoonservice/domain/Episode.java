package com.blockpage.webtoonservice.domain;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeContent;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeImage;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Episode {

    private String episodeTitle;
    private Integer episodeNumber;
    private String episodeThumbnail;
    private Integer totalScore;
    private Integer participantCount;
    private String uploadDate;
    private List<Image> images;
    private Long episodeId;
    private Integer commentCount;
    private double rating;
    private String author;
    private String authorWords;
    private String nextEpisodeTitle;
    private String nextEpisodeThumbnail;
    private double nextRating;
    private String nextUploadDate;
    private String webtoonStatus;
    private Integer episodePrice;


    @Getter
    @Builder
    @AllArgsConstructor
    public static class Image {

        String images;

        public static Image toImage(ResponseEpisodeImage responseEpisodeImage) {
            return Image.builder()
                .images(responseEpisodeImage.getEpisodeImage())
                .build();
        }
    }

    public static Episode toDomainFromEntity(EpisodeEntity episodeEntity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd");
        double rating = 0;
        if (episodeEntity.getParticipantCount() != 0) {
            rating = episodeEntity.getTotalScore() / episodeEntity.getParticipantCount();
        }
        return Episode.builder()
            .episodeId(episodeEntity.getId())
            .episodeTitle(episodeEntity.getEpisodeTitle())
            .episodeNumber(episodeEntity.getEpisodeNumber())
            .episodeThumbnail(episodeEntity.getEpisodeThumbnail())
            .episodePrice(episodeEntity.getEpisodePrice())
            .totalScore(episodeEntity.getTotalScore())
            .authorWords(episodeEntity.getAuthorWords())
            .participantCount(episodeEntity.getParticipantCount())
            .uploadDate(simpleDateFormat.format(episodeEntity.getUploadDate()))
            .rating(rating)
            .webtoonStatus(episodeEntity.getEpisodeStatus().getValue())
            .build();
    }

    public static Episode toDomainFromResponse(ResponseEpisodeDetail responseEpisodeDetail) {
        return Episode.builder()
            .images(responseEpisodeDetail.getImages().stream().map(Image::toImage).toList())
            .episodeId(responseEpisodeDetail.getEpisodeId())
            .commentCount(responseEpisodeDetail.getCommentCount())
            .rating(responseEpisodeDetail.getRating())
            .author(responseEpisodeDetail.getAuthor())
            .authorWords(responseEpisodeDetail.getAuthorWords())
            .nextEpisodeTitle(responseEpisodeDetail.getNextEpisodeTitle())
            .nextEpisodeThumbnail(responseEpisodeDetail.getNextEpisodeThumbnail())
            .nextRating(responseEpisodeDetail.getNextRating())
            .nextUploadDate(responseEpisodeDetail.getNextUploadDate())
            .build();
    }

    public static Episode fromResponseContent(ResponseEpisodeContent responseEpisodeContent){
        return Episode.builder()
            .episodeId(responseEpisodeContent.getEpisodeId())
            .episodeTitle(responseEpisodeContent.getEpisodeTitle())
            .episodeNumber(responseEpisodeContent.getEpisodeNumber())
            .author(responseEpisodeContent.getAuthor())
            .authorWords(responseEpisodeContent.getAuthor())
            .uploadDate(responseEpisodeContent.getUploadDate())
            .episodeThumbnail(responseEpisodeContent.getEpisodeThumbnail())
            .images(responseEpisodeContent.getImages().stream().map(Image::toImage).toList())
            .build();
    }

}
