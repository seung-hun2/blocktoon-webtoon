package com.blockpage.webtoonservice.adaptor.infrastructure.async.message;

import com.blockpage.webtoonservice.application.port.in.RatingUseCase;
import com.blockpage.webtoonservice.application.port.in.RatingUseCase.RatingQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RatingMessageListener {

    private final RatingUseCase webtoonService;
    private final RatingUseCase episodeService;

    @KafkaListener(
        topics = "${spring.kafka.ratingTopic}",
        groupId = "${spring.kafka.ratingGroup}",
        containerFactory = "ratingKafkaListenerContainerFactory"
    )
    public void listenWithHeaders(@Payload RatingMessage ratingMessage, @Headers MessageHeaders messageHeaders) {
        webtoonService.updateRating(RatingQuery.fromMessage(ratingMessage));
        episodeService.updateRating(RatingQuery.fromMessage(ratingMessage));
    }
}
