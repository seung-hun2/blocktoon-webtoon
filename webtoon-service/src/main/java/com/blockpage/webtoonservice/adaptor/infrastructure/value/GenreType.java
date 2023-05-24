package com.blockpage.webtoonservice.adaptor.infrastructure.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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
