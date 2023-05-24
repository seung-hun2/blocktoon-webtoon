package com.blockpage.webtoonservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    NO_FOUND_WEBTOON("해당 웹툰은 존재 하지 않습니다.", HttpStatus.NO_CONTENT),
    NO_FOUND_EPISODE("해당 에피소드가 존재 하지 않습니다.", HttpStatus.NO_CONTENT),
    NO_FOUND_DEMAND("해당 요청이 존재 하지 않습니다.", HttpStatus.NO_CONTENT),

    UNKNOWN_ERROR("알수없는 에러가 발생하였습니다.", HttpStatus.NO_CONTENT),     //오류 메세지 검토 필요
    ;

    private final String message;
    private final HttpStatus httpStatus;

}
