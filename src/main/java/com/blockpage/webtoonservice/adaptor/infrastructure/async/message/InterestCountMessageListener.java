package com.blockpage.webtoonservice.adaptor.infrastructure.async.message;

import com.blockpage.webtoonservice.application.port.in.InterestUseCase;
import com.blockpage.webtoonservice.application.port.in.InterestUseCase.InterestQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InterestCountMessageListener {

    private final InterestUseCase interestUseCase;

    @KafkaListener(
        topics = "${spring.kafka.interestTopic}",
        groupId = "${spring.kafka.interestGroup}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenWithHeaders(@Payload InterestCountMessage interestCountMessage, @Headers MessageHeaders messageHeaders) {
        interestUseCase.updateInterest(InterestQuery.toRequestInterest(interestCountMessage));
    }

}
