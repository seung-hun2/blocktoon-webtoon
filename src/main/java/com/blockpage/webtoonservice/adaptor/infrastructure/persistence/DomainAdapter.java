package com.blockpage.webtoonservice.adaptor.infrastructure.persistence;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.EpisodeEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.ImageEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.EpisodeRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.ImageRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.repository.WebtoonRepository;
import com.blockpage.webtoonservice.adaptor.infrastructure.value.WebtoonStatus;
import com.blockpage.webtoonservice.application.port.out.DemandPort;
import com.blockpage.webtoonservice.domain.Demand;
import com.blockpage.webtoonservice.domain.Episode;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Component
@RequiredArgsConstructor
public class DomainAdapter implements DemandPort {

    private final WebtoonRepository webtoonRepository;
    private final EpisodeRepository episodeRepository;
    private final ImageRepository imageRepository;
    private final Storage storage;
    private final String bucketName = "blockpage-bucket";

    @Override
    @Transactional
    public void postWebtoonDemand(Demand demand, String type, String target) throws IOException {

        String mainUUID = "", thumbnailUUID = "";
        BlobId blobId1, blobId2;
        BlobInfo blobInfo1, blobInfo2;

        if (target.equals("webtoon") && type.equals("enroll")) {
            mainUUID = UUID.randomUUID().toString();
            blobId1 = BlobId.of(bucketName, mainUUID);
            blobInfo1 = BlobInfo.newBuilder(blobId1)
                .setContentType(demand.getWebtoonMainImage().getContentType())
                .build();
            storage.create(blobInfo1, demand.getWebtoonMainImage().getBytes());

            thumbnailUUID = UUID.randomUUID().toString();
            blobId2 = BlobId.of(bucketName, thumbnailUUID);
            blobInfo2 = BlobInfo.newBuilder(blobId2)
                .setContentType(demand.getWebtoonThumbnail().getContentType())
                .build();
            storage.create(blobInfo2, demand.getWebtoonThumbnail().getBytes());

            WebtoonEntity webtoonEntity = WebtoonEntity.toEntity(demand, mainUUID, thumbnailUUID, 0);
            webtoonRepository.save(webtoonEntity);
            System.out.println("IF");
        } else if (target.equals("webtoon") && type.equals("modify")) {

            if (demand.getWebtoonThumbnail().getOriginalFilename().equals("") && demand.getWebtoonMainImage().getOriginalFilename()
                .equals("")) {
                Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findByWebtoonTitleAndCreatorIdAndWebtoonStatus(
                    demand.getWebtoonTitle(), demand.getCreatorId(), WebtoonStatus.PUBLISH);

                mainUUID = webtoonEntity.get().getWebtoonMainImage();
                thumbnailUUID = webtoonEntity.get().getWebtoonThumbnail();

            } else if (demand.getWebtoonThumbnail().getOriginalFilename().equals("")) {
                Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findByWebtoonTitleAndCreatorIdAndWebtoonStatus(
                    demand.getWebtoonTitle(), demand.getCreatorId(), WebtoonStatus.PUBLISH);

                thumbnailUUID = webtoonEntity.get().getWebtoonThumbnail();

                mainUUID = UUID.randomUUID().toString();
                blobId1 = BlobId.of(bucketName, mainUUID);
                blobInfo1 = BlobInfo.newBuilder(blobId1)
                    .setContentType(demand.getWebtoonMainImage().getContentType())
                    .build();
                storage.create(blobInfo1, demand.getWebtoonMainImage().getBytes());


            } else if (demand.getWebtoonMainImage().getOriginalFilename().equals("")) {
                Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findByWebtoonTitleAndCreatorIdAndWebtoonStatus(
                    demand.getWebtoonTitle(), demand.getCreatorId(), WebtoonStatus.PUBLISH);

                mainUUID = webtoonEntity.get().getWebtoonMainImage();

                thumbnailUUID = UUID.randomUUID().toString();
                blobId2 = BlobId.of(bucketName, thumbnailUUID);
                blobInfo2 = BlobInfo.newBuilder(blobId2)
                    .setContentType(demand.getWebtoonThumbnail().getContentType())
                    .build();
                storage.create(blobInfo2, demand.getWebtoonThumbnail().getBytes());
            } else {
                // 둘다 있을 때
                mainUUID = UUID.randomUUID().toString();
                blobId1 = BlobId.of(bucketName, mainUUID);
                blobInfo1 = BlobInfo.newBuilder(blobId1)
                    .setContentType(demand.getWebtoonMainImage().getContentType())
                    .build();
                storage.create(blobInfo1, demand.getWebtoonMainImage().getBytes());

                thumbnailUUID = UUID.randomUUID().toString();
                blobId2 = BlobId.of(bucketName, thumbnailUUID);
                blobInfo2 = BlobInfo.newBuilder(blobId2)
                    .setContentType(demand.getWebtoonThumbnail().getContentType())
                    .build();
                storage.create(blobInfo2, demand.getWebtoonThumbnail().getBytes());

            }

            WebtoonEntity webtoonEntity = WebtoonEntity.toEntity(demand, mainUUID, thumbnailUUID, 3);
            webtoonRepository.save(webtoonEntity);
            System.out.println("ELSEIF");
        }

    }

    @Override
    @Transactional
    public void removeWebtoonDemand(Demand demand) {
        Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findByWebtoonTitleAndCreatorIdAndWebtoonStatus(demand.getWebtoonTitle(),
            demand.getCreatorId(), WebtoonStatus.PUBLISH);
        webtoonEntity.get().update(WebtoonStatus.REMOVE_WAITING);
    }

    @Override
    @Transactional
    public void postEpisodeDemand(Demand demand, String type, String target) throws IOException, ParseException {

        EpisodeEntity e = null;
        String thumbnail = "";
        BlobId blobId1;
        BlobInfo blobInfo1;
        List<MultipartFile> multipartFileList;

        if (target.equals("episode") && type.equals("enroll")) {
            if (!demand.getEpisodeThumbnail().getOriginalFilename().equals("")) {
                thumbnail = UUID.randomUUID().toString();
                blobId1 = BlobId.of(bucketName, thumbnail);
                blobInfo1 = BlobInfo.newBuilder(blobId1)
                    .setContentType(demand.getEpisodeThumbnail().getContentType())
                    .build();
                storage.create(blobInfo1, demand.getEpisodeThumbnail().getBytes());
            }
            EpisodeEntity episodeEntity = EpisodeEntity.toEntity(demand, thumbnail, 1);

            e = episodeRepository.save(episodeEntity);
            System.out.println("IF");

        } else if (target.equals("episode") && type.equals("modify")) {
            if (!demand.getEpisodeThumbnail().getOriginalFilename().equals("")) {
                thumbnail = UUID.randomUUID().toString();
                blobId1 = BlobId.of(bucketName, thumbnail);
                blobInfo1 = BlobInfo.newBuilder(blobId1)
                    .setContentType(demand.getEpisodeThumbnail().getContentType())
                    .build();
                storage.create(blobInfo1, demand.getEpisodeThumbnail().getBytes());
            } else {
                //episodeRepository에서 찾아서 새로운 함수로 매핑 시켜줘야함
                Optional<EpisodeEntity> episodeEntity = episodeRepository.findByWebtoonIdAndEpisodeNumberAndEpisodeStatus(
                    demand.getWebtoonId(), demand.getEpisodeNumber(), WebtoonStatus.PUBLISH);
                thumbnail = episodeEntity.get().getEpisodeThumbnail();
            }

            EpisodeEntity episodeEntity = EpisodeEntity.toEntity(demand, thumbnail, 3);
            e = episodeRepository.save(episodeEntity);

        }

        // 에피소드 이미지가 수정되지 않았을 때 처리해줘야 함
        multipartFileList = demand.getEpisodeImages();
        for (int i = 1; i <= multipartFileList.size(); i++) {
            thumbnail = UUID.randomUUID().toString();
            blobId1 = BlobId.of(bucketName, thumbnail);
            blobInfo1 = BlobInfo.newBuilder(blobId1)
                .setContentType(multipartFileList.get(i - 1).getContentType())
                .build();
            storage.create(blobInfo1, multipartFileList.get(i - 1).getBytes());

            ImageEntity image = ImageEntity.toEntity(e.getWebtoonId(), e.getEpisodeNumber(), i,
                multipartFileList.get(i - 1).getName());
            imageRepository.save(image);
        }

    }

    @Override
    @Transactional
    public void removeEpisodeDemand(Demand demand) {
        Optional<EpisodeEntity> episodeEntity = episodeRepository.findByWebtoonIdAndEpisodeNumberAndEpisodeStatus(demand.getWebtoonId(),
            demand.getEpisodeNumber(), WebtoonStatus.PUBLISH);
        episodeEntity.get().update(WebtoonStatus.REMOVE_WAITING);
    }

    @Override
    @Transactional
    public void checkPostWebtoonDemand(Demand demand, String type, String target, String whether) {
        if (whether.equals("accept")) {

            Optional<WebtoonEntity> current = webtoonRepository.findByWebtoonTitleAndCreatorIdAndWebtoonStatus(demand.getWebtoonTitle(),
                demand.getCreatorId(), WebtoonStatus.PUBLISH);
            current.get().update(WebtoonStatus.findWebtoonStatusByKey(Integer.parseInt(type)));

            WebtoonEntity webtoon = WebtoonEntity.toEntity(demand, demand.getMain(), demand.getThumbnail(), 0);
            webtoonRepository.save(webtoon);

        } else if (whether.equals("refuse")) {
            webtoonRepository.deleteByWebtoonTitleAndCreatorIdAndWebtoonStatus(demand.getWebtoonTitle(), demand.getCreatorId(),
                WebtoonStatus.findWebtoonStatusByKey(Integer.parseInt(type)));
        }
    }

    @Override
    @Transactional
    public void checkRemoveWebtoonDemand(Demand demand, String whether) {
        if (whether.equals("accept")) {
            Optional<WebtoonEntity> current = webtoonRepository.findByWebtoonTitleAndCreatorIdAndWebtoonStatus(demand.getWebtoonTitle(),
                demand.getCreatorId(), WebtoonStatus.PUBLISH);
            current.get().update(WebtoonStatus.REMOVE);

            Optional<WebtoonEntity> webtoonEntity = webtoonRepository.findByWebtoonTitleAndCreatorIdAndWebtoonStatus(
                demand.getWebtoonTitle(), demand.getCreatorId(), WebtoonStatus.REMOVE_WAITING);
            webtoonEntity.get().update(WebtoonStatus.PUBLISH);

        } else if (whether.equals("refuse")) {
            webtoonRepository.deleteByWebtoonTitleAndCreatorIdAndWebtoonStatus(demand.getWebtoonTitle(), demand.getCreatorId(),
                WebtoonStatus.REMOVE_WAITING);
        }
    }

    @Override
    @Transactional
    public void checkPostEpisodeDemand(Demand demand, String type, String target, String whether) throws ParseException {
        if (whether.equals("refuse")) {
            // 거절되면 신청한게 신청거부로 상태변경 되어야 하고 , 끝
            // 확인되면 신청한게 publish 로 변경되어야하고, 그전에 있던게 삭제됨으로 이동 해야한다 .
            Optional<EpisodeEntity> current = episodeRepository.findById(demand.getEpisodeId());
            switch (type) {
                case "enroll":
                    current.get().update(WebtoonStatus.findWebtoonStatusByKey(2));
                    break;
                case "modify":
                    current.get().update(WebtoonStatus.findWebtoonStatusByKey(4));
                    break;
                case "remove":
                    current.get().update(WebtoonStatus.findWebtoonStatusByKey(6));
                    break;
            }

        } else if (whether.equals("accept")) {

            Optional<EpisodeEntity> current = episodeRepository.findById(demand.getEpisodeId());
            String episodeTitle = current.get().getEpisodeTitle();
            Optional<EpisodeEntity> episodeEntity = episodeRepository.findByEpisodeTitleAndEpisodeStatus(episodeTitle,
                WebtoonStatus.PUBLISH);
            episodeEntity.get().update(WebtoonStatus.REMOVE);
            current.get().update(WebtoonStatus.PUBLISH);

        }
    }

    @Override
    @Transactional
    public void checkRemoveEpisodeDemand(Demand demand, String whether) {

        if (whether.equals("accept")) {
            Optional<EpisodeEntity> current = episodeRepository.findByEpisodeTitleAndCreatorIdAndEpisodeStatus(
                demand.getWebtoonTitle(), demand.getCreatorId(), WebtoonStatus.PUBLISH);
            current.get().update(WebtoonStatus.REMOVE);

            Optional<EpisodeEntity> episodeEntity = episodeRepository.findByEpisodeTitleAndCreatorIdAndEpisodeStatus(
                demand.getWebtoonTitle(), demand.getCreatorId(), WebtoonStatus.REMOVE_WAITING);
            episodeEntity.get().update(WebtoonStatus.PUBLISH);

        } else if (whether.equals("refuse")) {
            episodeRepository.deleteByEpisodeTitleAndCreatorIdAndEpisodeStatus(demand.getWebtoonTitle(), demand.getCreatorId(),
                WebtoonStatus.REMOVE_WAITING);
        }
    }

    @Override
    public List<Demand> getWebtoonDemand(Demand demand, String type) throws IOException {

        List<WebtoonEntity> webtoonEntityList = switch (type) {
            case "modify" -> webtoonRepository.findByCreatorIdAndWebtoonStatus(demand.getCreatorId(),
                WebtoonStatus.findWebtoonStatusByKey(3));
            case "remove" -> webtoonRepository.findByCreatorIdAndWebtoonStatus(demand.getCreatorId(),
                WebtoonStatus.findWebtoonStatusByKey(5));
            default -> null;
        };

        return webtoonEntityList != null ? webtoonEntityList.stream().map(Demand::toDomainFromWebtoonEntity).toList() : null;

//        List<Webtoon> webtoonList = webtoonEntityList != null ? webtoonEntityList.stream().map(Webtoon::toDomainFromEntity).toList() : null;
//        List<Demand> demandList = null;
//        for (int i = 0; i < (webtoonList != null ? webtoonList.size() : 0); i++) {
//            File file = new File(webtoonList.get(i).getWebtoonMainImage());
//            DiskFileItem fileItem = new DiskFileItem("mainImage", Files.probeContentType(file.toPath()), false, file.getName(),
//                (int) file.length(), file.getParentFile());
//            InputStream input = new FileInputStream(file);
//            OutputStream os = fileItem.getOutputStream();
//            IOUtils.copy(input, os);
//            MultipartFile multipartFile = new CommonsMultipartFile((FileItem) fileItem);
//
//            file = new File(webtoonList.get(i).getWebtoonThumbnail());
//            fileItem = new DiskFileItem("thumbnail", Files.probeContentType(file.toPath()), false, file.getName(),
//                (int) file.length(), file.getParentFile());
//            input = new FileInputStream(file);
//            os = fileItem.getOutputStream();
//            IOUtils.copy(input, os);
//            MultipartFile multipartFile1 = new CommonsMultipartFile((FileItem) fileItem);
//
//            demandList.add(Demand.toDomainFromEntity(webtoonList.get(i), multipartFile, multipartFile1));
//        }

//        return demandList;
    }

    @Override
    public List<Demand> getEpisodeDemand(Demand demand, String type) {

        List<EpisodeEntity> episodeEntityList = null;
        switch (type) {
            case "enroll":
                episodeEntityList = episodeRepository.findByCreatorIdAndEpisodeStatus(demand.getCreatorId(),
                    WebtoonStatus.findWebtoonStatusByKey(1));
                break;
            case "modify":
                episodeEntityList = episodeRepository.findByCreatorIdAndEpisodeStatus(demand.getCreatorId(),
                    WebtoonStatus.findWebtoonStatusByKey(3));
                break;
            case "remove":
                episodeEntityList = episodeRepository.findByCreatorIdAndEpisodeStatus(demand.getCreatorId(),
                    WebtoonStatus.findWebtoonStatusByKey(5));
                break;
        }
        List<Episode> episodeList = episodeEntityList != null ? episodeEntityList.stream().map(Episode::toDomainFromEntity).toList() : null;
        List<Demand> demandList = null;

        return episodeEntityList != null ?
            episodeEntityList.stream().map(Demand::toDomainFromEpisodeEntity).collect(Collectors.toList()) : null;
    }
}