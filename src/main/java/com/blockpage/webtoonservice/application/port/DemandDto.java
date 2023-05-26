package com.blockpage.webtoonservice.application.port;

import com.blockpage.webtoonservice.domain.Demand;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class DemandDto {

    private String creatorId;
    private String creator;
    private String webtoonTitle;
    private String webtoonDescription;
    private String authorWords;
    private Integer genre;
    private Integer publicationDays;
    private String illustrator;
    private MultipartFile webtoonMainImage;
    private MultipartFile webtoonThumbnail;

    private Long webtoonId;
    private Integer episodeNumber;
    private String episodeTitle;
    private String uploadDate;
    private MultipartFile episodeThumbnail;
    private List<MultipartFile> episodeImages;

    public static DemandDto toDtoFromDomain(Demand demand) {
        return DemandDto.builder()
            .creatorId(demand.getCreatorId())
            .creator(demand.getCreator())
            .webtoonId(demand.getWebtoonId())
            .webtoonTitle(demand.getWebtoonTitle())
            .webtoonDescription(demand.getWebtoonDescription())
            .genre(demand.getGenre())
            .publicationDays(demand.getPublicationDays())
            .illustrator(demand.getIllustrator())
            .webtoonMainImage(demand.getWebtoonMainImage())
            .webtoonThumbnail(demand.getWebtoonThumbnail())
            .episodeNumber(demand.getEpisodeNumber())
            .episodeTitle(demand.getEpisodeTitle())
            .uploadDate(demand.getUploadDate())
            .authorWords(demand.getAuthorWords())
            .episodeThumbnail(demand.getEpisodeThumbnail())
            .episodeImages(demand.getEpisodeImages())
            .build();
    }
}
