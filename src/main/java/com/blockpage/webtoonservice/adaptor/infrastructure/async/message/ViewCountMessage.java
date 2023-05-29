package com.blockpage.webtoonservice.adaptor.infrastructure.async.message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewCountMessage {

    private Long webtoonId;
    private Integer viewCount;
}
