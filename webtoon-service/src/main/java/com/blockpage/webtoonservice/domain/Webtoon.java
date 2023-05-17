package com.blockpage.webtoonservice.domain;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Webtoon {

    private String webtoonTitle;
    private String webtoonDescription;
    private Long creatorId;
    private String creator;
    private String illustrator;
    private PublicationDays publicationDays;
    private String webtoonMainImage;
    private String webtoonThumbnail;
    private GenreType genre;
    private int interestCount;
    private int views;

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

        private int key;
        private String value;

        public static GenreType findGenreTypeByKey(int key) {
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

        int key;
        String value;

        public static PublicationDays findPublicationDaysByKey(int key) {
            return Arrays.stream(PublicationDays.values())
                .filter(k -> k.getKey() == key)
                .findFirst()
                .get();
        }
    }

    public static Webtoon toDomainFromEntity(WebtoonEntity webtoon) {
        return Webtoon.builder()
            .webtoonTitle(webtoon.getWebtoonTitle())
            .webtoonMainImage(webtoon.getWebtoonMainImage())
            .creator(webtoon.getCreator())
            .illustrator(webtoon.getIllustrator())
            .views(webtoon.getViews())
            .interestCount(webtoon.getInterestCount())
            .genre(GenreType.findGenreTypeByKey(webtoon.getGenreType().getKey()))
            .publicationDays(PublicationDays.findPublicationDaysByKey(webtoon.getPublicationDays().getKey()))
            .build();
    }


}
