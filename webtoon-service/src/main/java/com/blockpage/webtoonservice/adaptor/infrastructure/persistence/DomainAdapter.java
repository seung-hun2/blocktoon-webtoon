package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.GenreType;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.in.WebtoonUseCase.RequestWebtoon;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DomainAdapter {

    private final WebtoonRepository webtoonRepository;
    private final Storage storage;
    private String bucketName = "blockpage-bucket";

    public WebtoonEntity toEntity(RequestWebtoon webtoon, String webtoonMainImage, String webtoonThumbnail) {
        return WebtoonEntity.builder()
            .creator(webtoon.getCreator())
            .webtoonStatus(WebtoonStatus.PUBLISH)
            .webtoonDescription(webtoon.getWebtoonDescription())
            .webtoonTitle(webtoon.getWebtoonTitle())
            .webtoonMainImage(webtoonMainImage)
            .webtoonThumbnail(webtoonThumbnail)
            .creatorId(webtoon.getCreatorId())
            .genreType(GenreType.findGenreTypeByKey(webtoon.getGenre()))
            .publicationDays(PublicationDays.findPublicationDaysByKey(webtoon.getPublicationDays()))
            .interestCount(0)
            .illustrator(webtoon.getIllustrator())
            .views(0)
            .build();
    }

    public Long webtoonEnroll(@RequestBody RequestWebtoon webtoon, MultipartFile main, MultipartFile thumbnail) throws IOException {

        String mainUUID = UUID.randomUUID().toString();
        BlobId blobId1 = BlobId.of(bucketName, mainUUID);
        BlobInfo blobInfo1 = BlobInfo.newBuilder(blobId1)
            .setContentType(main.getContentType())
            .build();
        storage.create(blobInfo1, main.getBytes());

        String thumbnailUUID = UUID.randomUUID().toString();
        BlobId blobId2 = BlobId.of(bucketName, thumbnailUUID);
        BlobInfo blobInfo2 = BlobInfo.newBuilder(blobId2)
            .setContentType(thumbnail.getContentType())
            .build();
        storage.create(blobInfo2, thumbnail.getBytes());

        WebtoonEntity webtoonEntity = toEntity(webtoon, mainUUID, thumbnailUUID);

        WebtoonEntity webtoons = webtoonRepository.save(webtoonEntity);
        return webtoons.getId();
    }

}
