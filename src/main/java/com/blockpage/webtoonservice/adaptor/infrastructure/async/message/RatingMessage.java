package com.blockpage.webtoonservice.adaptor.infrastructure.async.message;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingMessage {

    private Long webtoonId;
    private Long episodeId;
    private Integer totalScore;
    private Integer participantCount;

}
