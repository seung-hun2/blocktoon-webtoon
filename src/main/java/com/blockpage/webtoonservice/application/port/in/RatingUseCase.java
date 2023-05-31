package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.adaptor.infrastructure.async.message.RatingMessage;
import com.blockpage.webtoonservice.adaptor.infrastructure.async.message.ViewCountMessage;
import com.blockpage.webtoonservice.application.port.in.ViewCountUseCase.viewCountQuery;
import lombok.Builder;
import lombok.Getter;

public interface RatingUseCase {

    void updateRating(RatingQuery ratingQuery);

    @Getter
    @Builder
    class RatingQuery {

        private Long webtoonId;
        private Long episodeId;
        private Integer totalScore;
        private Integer participantCount;

        public static RatingQuery fromMessage(RatingMessage ratingMessage) {
            return RatingQuery.builder()
                .webtoonId(ratingMessage.getWebtoonId())
                .episodeId(ratingMessage.getEpisodeId())
                .totalScore(ratingMessage.getTotalScore())
                .participantCount(ratingMessage.getParticipantCount())
                .build();
        }
    }

}
