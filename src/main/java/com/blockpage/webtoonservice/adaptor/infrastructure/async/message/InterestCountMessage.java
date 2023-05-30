package com.blockpage.webtoonservice.adaptor.infrastructure.async.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterestCountMessage {

    private Long webtoonId;
    private Integer interestCount;

}
