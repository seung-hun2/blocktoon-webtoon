package com.blockpage.webtoonservice.application.port.in;

import com.blockpage.webtoonservice.adaptor.infrastructure.async.message.ViewCountMessage;
import lombok.Builder;
import lombok.Getter;

public interface ViewCountUseCase {

    void updateViewCount(viewCountQuery viewCountQuery);

    @Getter
    @Builder
    class viewCountQuery {

        private Long webtoonId;
        private Integer viewCount;

        public static viewCountQuery toViewCount(ViewCountMessage viewCountMessage) {
            return viewCountQuery.builder()
                .webtoonId(viewCountMessage.getWebtoonId())
                .viewCount(viewCountMessage.getViewCount())
                .build();
        }
    }

}
