package com.blockpage.webtoonservice.adaptor.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum WebtoonStatus {
    PUBLISH(0, "배포중"),
    REGISTRATION_WAITING(1, "등록 요청"),
    REGISTRATION_REJECTION(2, "등록 거부"),
    MODIFICATION_WAITING(3, "수정 요청"),
    MODIFICATION_REJECTING(4, "수정 거부"),
    REMOVE_WAITING(5, "삭제 요청"),
    REMOVE(6, "삭제됨"),
    ;

    int key;
    String value;

    public static String findPublicationDaysByKey(int key) {
        return Arrays.stream(PublicationDays.values())
            .filter(k -> k.getKey() == key)
            .findFirst()
            .get().toString();
    }
}
