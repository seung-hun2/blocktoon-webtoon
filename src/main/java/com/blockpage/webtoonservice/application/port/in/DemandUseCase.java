package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.application.port.DemandDto;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public interface DemandUseCase {

    void postDemand(DemandQuery demandQuery) throws IOException, ParseException;

    void checkDemand(DemandQuery demandQuery) throws IOException, ParseException;

    List<DemandDto> getDemand(DemandQuery demandQuery) throws IOException;

    @Getter
    @Builder
    class DemandQuery {

        String target;
        String type;
        String whether;
        Long creatorId;
        Long id;
        RequestDemand requestDemand;
        MultipartFile webtoonMainImage;
        MultipartFile webtoonThumbnail;
        MultipartFile episodeThumbnail;
        List<MultipartFile> episodeImage;

        public static DemandQuery toQueryFromWebtoon(Long creatorId, String target, String type, RequestDemand requestDemand,
            MultipartFile webtoonMainImage, MultipartFile webtoonThumbnail) {
            return DemandQuery.builder()
                .creatorId(creatorId)
                .target(target)
                .type(type)
                .requestDemand(requestDemand)
                .webtoonMainImage(webtoonMainImage)
                .webtoonThumbnail(webtoonThumbnail)
                .build();
        }

        public static DemandQuery toQueryFromEpisode(Long creatorId, String target, String type, RequestDemand requestDemand,
            MultipartFile episodeThumbnail, List<MultipartFile> episodeImage) {
            return DemandQuery.builder()
                .creatorId(creatorId)
                .target(target)
                .type(type)
                .requestDemand(requestDemand)
                .episodeThumbnail(episodeThumbnail)
                .episodeImage(episodeImage)
                .build();
        }

        public static DemandQuery toQueryFromId(String target, String type, String whether, Long creatorId, Long id) {
            return DemandQuery.builder()
                .id(id)
                .creatorId(creatorId)
                .target(target)
                .type(type)
                .whether(whether)
                .build();
        }

        public static DemandQuery toQueryFromId(String target, String type, Long id){
            return DemandQuery.builder()
                .target(target)
                .type(type)
                .id(id)
                .build();
        }


    }
}
