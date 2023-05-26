package com.blockpage.webtoonservice.adaptor.web.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class MessageView {

    String message;

    public MessageView(String message) {
        this.message = message;
    }
}
