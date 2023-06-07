package com.blockpage.webtoonservice.domain;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.application.port.in.DemandUseCase.DemandQuery;
import java.text.SimpleDateFormat;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class Demand {

    private String creatorId;
    private String creator;
    private String webtoonTitle;
    private String webtoonDescription;
    private Integer genre;
    private Integer publicationDays;
    private String illustrator;
    private MultipartFile webtoonMainImage;
    private MultipartFile webtoonThumbnail;
    private String webtoonStatus;

    private Integer episodeNumber;
    private Long webtoonId;
    private String episodeTitle;
    private String uploadDate;
    private String authorWords;
    private MultipartFile episodeThumbnail;
    private List<MultipartFile> episodeImages;


    private String type;
    private String target;
    private Long id;

    private String main;
    private String thumbnail;
    private List<String> images;
    private Long episodeId;


    public static Demand toDomainFromWebtoon(DemandQuery demandQuery) {
        return Demand.builder()
            .creatorId(demandQuery.getCreatorId())
            .creator(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getCreatorNickname() : null)
            .webtoonId(demandQuery.getWebtoonId())
            .id(demandQuery.getId())
            .webtoonTitle(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getWebtoonTitle() : null)
            .webtoonDescription(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getWebtoonDescription() : null)
            .genre(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getGenre() : null)
            .publicationDays(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getPublicationDays() : null)
            .illustrator(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getIllustrator() : null)
            .webtoonMainImage(demandQuery.getWebtoonMainImage())
            .webtoonThumbnail(demandQuery.getWebtoonThumbnail())
            .webtoonStatus(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getWebtoonStatus() : null)
            .build();
    }

    public static Demand toDomainFromGet(DemandQuery demandQuery) {
        return Demand.builder()
            .target(demandQuery.getTarget())
            .type(demandQuery.getType())
            .build();
    }

    public static Demand toDomainFromEpisode(DemandQuery demandQuery) {
        return Demand.builder()
            .creatorId(demandQuery.getCreatorId())
            .episodeId(demandQuery.getId())
            .webtoonId(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getWebtoonId() : null)
            .episodeNumber(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getEpisodeNumber() : null)
            .episodeTitle(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getEpisodeTitle() : null)
            .uploadDate(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getUploadDate() : null)
            .authorWords(demandQuery.getRequestDemand() != null ? demandQuery.getRequestDemand().getAuthorWords() : null)
            .episodeThumbnail(demandQuery.getEpisodeThumbnail())
            .episodeImages(demandQuery.getEpisodeImage())
            .build();
    }


    public static Demand toDomainFromWebtoonEntity(WebtoonEntity webtoon) {
        return Demand.builder()
            .creatorId(webtoon.getCreatorId())
            .creator(webtoon.getCreator())
            .webtoonId(webtoon.getId())
            .webtoonTitle(webtoon.getWebtoonTitle())
            .webtoonDescription(webtoon.getWebtoonDescription())
            .genre(webtoon.getGenreType() != null ? webtoon.getGenreType().getKey() : null)
            .publicationDays(webtoon.getPublicationDays() != null ? webtoon.getPublicationDays().getKey() : null)
            .illustrator(webtoon.getIllustrator())
            .main(webtoon.getWebtoonMainImage())
            .thumbnail(webtoon.getWebtoonThumbnail())
            .build();
    }

    public static Demand toDomainFromEpisodeEntity(EpisodeEntity episode) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy.MM.dd");
        return Demand.builder()
            .creatorId(episode.getCreatorId())
            .webtoonId(episode.getWebtoonId())
            .episodeId(episode.getId())
            .episodeNumber(episode.getEpisodeNumber())
            .episodeTitle(episode.getEpisodeTitle())
            .uploadDate(simpleDateFormat.format(episode.getUploadDate()))
            .authorWords(episode.getAuthorWords())
            .thumbnail(episode.getEpisodeThumbnail())
            .build();
    }
}
