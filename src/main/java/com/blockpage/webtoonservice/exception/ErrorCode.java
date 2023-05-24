package com.blockpage.webtoonservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {


    ;

    private final String message;
    private final HttpStatus httpStatus;

}
