package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.application.port.DemandDto;
import com.blockpage.webtoonservice.domain.Webtoon.GenreType;
import com.blockpage.webtoonservice.domain.Webtoon.PublicationDays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DemandView {

    private String webtoonTitle;
    private String webtoonDescription;
    private String genre;
    private String publicationDays;
    private String illustrator;
    private String webtoonMainImage;
    private String webtoonThumbnail;
    private Integer episodeStatus;

    private String episodeTitle;
    private String uploadDate;
    private String authorWords;
    private String episodeThumbnail;
    private List<Images> episodeImages;
    private Integer webtoonStatus;

    @Getter
    public static class Images {

        private String image;

        public Images(String image) {
            this.image = image;
        }
    }

    public DemandView(String webtoonTitle, String episodeTitle, String uploadDate, String authorWords, String episodeThumbnail,
        List<Images> episodeImages, Integer webtoonStatus) {
        this.webtoonTitle = webtoonTitle;
        this.episodeTitle = episodeTitle;
        this.uploadDate = uploadDate;
        this.authorWords = authorWords;
        this.episodeThumbnail = episodeThumbnail;
        this.episodeImages = episodeImages;
        this.webtoonStatus = webtoonStatus;
    }

    public DemandView(String webtoonTitle, String webtoonDescription, String genre, String publicationDays, String illustrator,
        String webtoonMainImage, String webtoonThumbnail, Integer episodeStatus) {
        this.webtoonTitle = webtoonTitle;
        this.webtoonDescription = webtoonDescription;
        this.genre = genre;
        this.publicationDays = publicationDays;
        this.illustrator = illustrator;
        this.webtoonMainImage = webtoonMainImage;
        this.webtoonThumbnail = webtoonThumbnail;
        this.episodeStatus = episodeStatus;
    }

    public static DemandView toView(DemandDto demandDto) {
        return DemandView.builder()
            .webtoonTitle(demandDto.getWebtoonTitle())
            .webtoonDescription(demandDto.getWebtoonDescription())
            .genre(demandDto.getGenre() != null ? GenreType.findGenreTypeByKey(demandDto.getGenre()).toString() : null)
            .publicationDays(demandDto.getPublicationDays() != null ? PublicationDays.findPublicationDaysByKey(demandDto.getPublicationDays()).toString() : null)
            .webtoonMainImage(demandDto.getWebtoonMainImage() != null ? demandDto.getWebtoonMainImage().getName() : null)
            .webtoonThumbnail(demandDto.getWebtoonThumbnail() != null ? demandDto.getWebtoonThumbnail().getName() : null)
            .episodeTitle(demandDto.getEpisodeTitle() != null ? demandDto.getEpisodeTitle() : null)
            .uploadDate(demandDto.getUploadDate() != null ? demandDto.getUploadDate().toString() : null)
            .authorWords(demandDto.getAuthorWords() != null ? demandDto.getAuthorWords() : null)
            .build();
    }
}
