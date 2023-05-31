package com.blockpage.webtoonservice.adaptor.infrastructure.async.config;

import com.blockpage.webtoonservice.adaptor.infrastructure.async.message.InterestCountMessage;
import com.blockpage.webtoonservice.adaptor.infrastructure.async.message.RatingMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class RatingConsumerConfig {

    @Value("${spring.kafka.bootstrapAddress}")
    private String bootStrapServer;

    @Value("${spring.kafka.ratingGroup}")
    private String groupName;

    @Bean
    public ConsumerFactory<String, RatingMessage> ratingConsumerFactory() {
        JsonDeserializer<RatingMessage> deserializer = new JsonDeserializer<>(RatingMessage.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(consumerFactoryConfig(deserializer), new StringDeserializer(), deserializer);
    }

    private Map<String, Object> consumerFactoryConfig(JsonDeserializer<RatingMessage> deserializer) {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RatingMessage> ratingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RatingMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ratingConsumerFactory());
        return factory;
    }
}