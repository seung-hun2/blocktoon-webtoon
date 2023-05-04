package com.blockpage.webtoonservice.adaptor.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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
