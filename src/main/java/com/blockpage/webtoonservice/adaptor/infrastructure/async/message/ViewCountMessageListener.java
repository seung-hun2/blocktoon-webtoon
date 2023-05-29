package com.blockpage.webtoonservice.adaptor.infrastructure.async.message;

import com.blockpage.webtoonservice.application.port.in.ViewCountUseCase;
import com.blockpage.webtoonservice.application.port.in.ViewCountUseCase.viewCountQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewCountMessageListener {

    private final ViewCountUseCase viewCountUseCase;

    @KafkaListener(
        topics = "${spring.kafka.viewTopic}",
        groupId = "${spring.kafka.viewGroup}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenWithHeaders(@Payload ViewCountMessage viewCountMessage, @Headers MessageHeaders messageHeaders) {
        viewCountUseCase.updateViewCount(viewCountQuery.toViewCount(viewCountMessage));
    }
}
