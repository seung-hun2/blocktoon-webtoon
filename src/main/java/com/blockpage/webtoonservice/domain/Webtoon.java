package com.blockpage.webtoonservice.domain;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Webtoon {

    private Long webtoonId;
    private String webtoonTitle;
    private String webtoonDescription;
    private String creatorId;
    private String creator;
    private String illustrator;
    private PublicationDays publicationDays;
    private String webtoonMainImage;
    private String webtoonThumbnail;
    private String webtoonStatus;
    private GenreType genre;
    private Integer interestCount;
    private Integer views;
    private String cutoutImage;

    @Getter
    @AllArgsConstructor
    public enum GenreType {
        FANTASY_DRAMA(0, "판타지 드라마"),
        ROMANCE(1, "로맨스"),
        FANTASY(2, "판타지"),
        ROMANCE_FANTASY(3, "로맨스 판타지"),
        ACTION(4, "액션"),
        DRAMA(5, "드라마"),
        HORROR(6, "공포"),
        COMIC(7, "코믹"),
        ;

        private Integer key;
        private String value;

        public static GenreType findGenreTypeByKey(Integer key) {
            return Arrays.stream(GenreType.values())
                .filter(t -> t.getKey() == key)
                .findFirst().get();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum PublicationDays {
        MONDAY(0, "월"),
        TUESDAY(1, "화"),
        WEDNESDAY(2, "수"),
        THURSDAY(3, "목"),
        FRIDAY(4, "금"),
        SATURDAY(5, "토"),
        SUNDAY(6, "일");

        Integer key;
        String value;

        public static PublicationDays findPublicationDaysByKey(Integer key) {
            return Arrays.stream(PublicationDays.values())
                .filter(k -> k.getKey() == key)
                .findFirst()
                .get();
        }
    }

    public static Webtoon toDomainFromEntity(WebtoonEntity webtoon) {
        return Webtoon.builder()
            .webtoonId(webtoon.getId())
            .webtoonTitle(webtoon.getWebtoonTitle())
            .webtoonMainImage(webtoon.getWebtoonMainImage())
            .webtoonThumbnail(webtoon.getWebtoonThumbnail())
            .webtoonDescription(webtoon.getWebtoonDescription())
            .creator(webtoon.getCreator())
            .creatorId(webtoon.getCreatorId())
            .illustrator(webtoon.getIllustrator())
            .views(webtoon.getViews())
            .interestCount(webtoon.getInterestCount())
            .webtoonStatus(webtoon.getWebtoonStatus().getValue())
            .genre(GenreType.findGenreTypeByKey(webtoon.getGenreType().getKey()))
            .publicationDays(PublicationDays.findPublicationDaysByKey(webtoon.getPublicationDays().getKey()))
            .cutoutImage(webtoon.getCutoutImage())
            .build();
    }


}
