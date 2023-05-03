package com.blockpage.webtoonservice.adaptor.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PublicationDays {
    MONDAY(0, "월"),
    TUESDAY(0, "화"),
    WEDNESDAY(0, "수"),
    THURSDAY(0, "목"),
    FRIDAY(0, "금"),
    SATURDAY(0, "토"),
    SUNDAY(0, "일");

    int key;
    String value;

    public static PublicationDays findPublicationDaysByKey(int key){
        return Arrays.stream(PublicationDays.values())
                .filter(k -> k.getKey()==key)
                .findFirst()
                .get();
    }
}
