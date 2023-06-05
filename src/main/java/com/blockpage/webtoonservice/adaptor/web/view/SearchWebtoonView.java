package com.blockpage.webtoonservice.adaptor.web.view;

import com.blockpage.webtoonservice.adaptor.infrastructure.value.PublicationDays;
import com.blockpage.webtoonservice.application.port.out.ResponseWebtoon;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SearchWebtoonView {
    private Long webtoonId;
    private String webtoonTitle;
    private String webtoonThumbnail;
    private String creator;
    private String illustrator;
    private Integer views;
    private Integer interestCount;
    private String genre;
    private String webtoonStatus;

    private String publicationDays;
    private String webtoonDescription;

    public SearchWebtoonView(Long webtoonId, String webtoonTitle, String webtoonThumbnail, String creator, String illustrator,
        Integer views,
        Integer interestCount, String genre, String webtoonStatus, String publicationDays, String webtoonDescription) {
        this.webtoonId = webtoonId;
        this.webtoonTitle = webtoonTitle;
        this.webtoonThumbnail = webtoonThumbnail;
        this.creator = creator;
        this.illustrator = illustrator;
        this.views = views;
        this.interestCount = interestCount;
        this.genre = genre;
        this.webtoonStatus = webtoonStatus;
        this.publicationDays = publicationDays;
        this.webtoonDescription = webtoonDescription;
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

        public static String findPublicationDaysByKey(Integer key) {
            return Arrays.stream(PublicationDays.values())
                .filter(k -> k.getKey() == key)
                .findFirst()
                .get().getValue();
        }
    }
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

        public static String findGenreTypeByKey(Integer key) {
            return Arrays.stream(GenreType.values())
                .filter(t -> t.getKey() == key)
                .findFirst().get().getValue();
        }

    }

    public static SearchWebtoonView searchView(ResponseWebtoon webtoon){
        return new SearchWebtoonView(
            webtoon.getWebtoonId(),
            webtoon.getWebtoonTitle(),
            webtoon.getWebtoonThumbnail(),
            webtoon.getCreator(),
            webtoon.getIllustrator(),
            webtoon.getViews(),
            webtoon.getInterestCount(),
            GenreType.findGenreTypeByKey(webtoon.getGenreType()),
            webtoon.getWebtoonStatus(),
            PublicationDays.findPublicationDaysByKey(webtoon.getPublicationDays()),
            webtoon.getDescription()
        );
    }
}
