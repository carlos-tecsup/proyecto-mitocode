package com.tecsup.microservices.license_command_service.util;

import com.tecsup.microservices.common_models.entity.GenericEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {


    @Value("${kafka.license.topicName:license}")
    private String topicName;

    private final KafkaTemplate<String, GenericEntity<?>> kafkaTemplate;

    public void sendMessage(GenericEntity<?> message) {
        kafkaTemplate.send(topicName, message);
    }

}
