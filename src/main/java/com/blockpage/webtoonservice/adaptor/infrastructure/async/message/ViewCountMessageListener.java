package com.blockpage.webtoonservice.adaptor.infrastructure.async.message;

import com.blockpage.webtoonservice.application.port.in.ViewCountUseCase;
import com.blockpage.webtoonservice.application.port.in.ViewCountUseCase.viewCountQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ViewCountMessageListener {

    private final ViewCountUseCase viewCountUseCase;

    @KafkaListener(
        topics = "${spring.kafka.viewTopic}",
        groupId = "${spring.kafka.viewGroup}",
        containerFactory = "viewKafkaListenerContainerFactory"
    )
    public void listenWithHeaders(@Payload ViewCountMessage viewCountMessage, @Headers MessageHeaders messageHeaders) {
        log.info("webtoonId : " + viewCountMessage.getWebtoonId());
        log.info("viewCount : "+viewCountMessage.getViewCount());
        viewCountUseCase.updateViewCount(viewCountQuery.toViewCount(viewCountMessage));
    }
}
