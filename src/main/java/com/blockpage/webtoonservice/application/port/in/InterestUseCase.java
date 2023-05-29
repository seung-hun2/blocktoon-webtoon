package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.adaptor.infrastructure.async.message.InterestCountMessage;
import lombok.Builder;
import lombok.Getter;

public interface InterestUseCase {

    void updateInterest(InterestQuery interestQuery);

    @Getter
    @Builder
    class InterestQuery {

        private Long webtoonId;
        private Integer interestCount;

        public static InterestQuery toRequestInterest(InterestCountMessage interestCountMessage) {
            return InterestQuery.builder()
                .webtoonId(interestCountMessage.getWebtoonId())
                .interestCount(interestCountMessage.getInterestCount())
                .build();
        }
    }

}
