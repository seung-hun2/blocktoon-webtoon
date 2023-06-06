package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisode;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeContent;
import com.blockpage.webtoonservice.application.port.out.ResponseEpisodeDetail;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EpisodeView {


    private Long episodeId;
    private String episodeTitle;
    private Integer episodeNumber;
    private String author;
    private String authorWords;
    private String uploadDate;
    private String episodeThumbnail;
    private List<EpisodeImageView> images;


    public EpisodeView(Long episodeId, String episodeTitle, Integer episodeNumber, String author, String authorWords, String uploadDate,
        String episodeThumbnail, List<EpisodeImageView> images) {
        this.episodeId = episodeId;
        this.episodeTitle = episodeTitle;
        this.episodeNumber = episodeNumber;
        this.author = author;
        this.authorWords = authorWords;
        this.uploadDate = uploadDate;
        this.episodeThumbnail = episodeThumbnail;
        this.images = images;
    }

    public static EpisodeView toViewFromResponse(ResponseEpisodeContent responseEpisodeContent) {
        return EpisodeView.builder()
            .episodeId(responseEpisodeContent.getEpisodeId())
            .episodeTitle(responseEpisodeContent.getEpisodeTitle())
            .episodeNumber(responseEpisodeContent.getEpisodeNumber())
            .author(responseEpisodeContent.getAuthor())
            .authorWords(responseEpisodeContent.getAuthorWords())
            .uploadDate(responseEpisodeContent.getUploadDate())
            .episodeThumbnail(responseEpisodeContent.getEpisodeThumbnail())
            .images(responseEpisodeContent.getImages().stream().map(EpisodeImageView::toViewFromResponse).toList())
            .build();
    }

}
