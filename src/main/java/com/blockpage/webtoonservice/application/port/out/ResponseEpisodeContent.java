package com.blockpage.webtoonservice.application.port.out;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.domain.Episode;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseEpisodeContent {

    private Long episodeId;
    private String episodeTitle;
    private Integer episodeNumber;
    private String author;
    private String authorWords;
    private String uploadDate;
    private String episodeThumbnail;
    private List<ResponseEpisodeImage> images;

    public static ResponseEpisodeContent fromEntity(EpisodeEntity episode, List<ImageEntity> image){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd");
        return ResponseEpisodeContent.builder()
            .episodeId(episode.getId())
            .episodeTitle(episode.getEpisodeTitle())
            .episodeNumber(episode.getEpisodeNumber())
            .author(episode.getCreatorId())
            .authorWords(episode.getAuthorWords())
            .uploadDate(simpleDateFormat.format(episode.getUploadDate()))
            .episodeThumbnail(episode.getEpisodeThumbnail())
            .images(image.stream().map(ResponseEpisodeImage::toResponseFromEntity).toList())
            .build();
    }

    public static ResponseEpisodeContent fromDomain(Episode episode){
        return ResponseEpisodeContent.builder()
            .episodeId(episode.getEpisodeId())
            .episodeTitle(episode.getEpisodeTitle())
            .episodeNumber(episode.getEpisodeNumber())
            .author(episode.getAuthor())
            .authorWords(episode.getAuthorWords())
            .uploadDate(episode.getUploadDate())
            .episodeThumbnail(episode.getEpisodeThumbnail())
            .images(episode.getImages().stream().map(ResponseEpisodeImage::toResponseFromDomain).toList())
            .build();
    }

}
