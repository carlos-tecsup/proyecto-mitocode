package com.tecsup.microservices.audit_service.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.microservices.audit_service.repository.LicenseRepository;
import com.tecsup.microservices.common_models.entity.DriverLicenseEntityMongo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.tecsup.microservices.common_models.entity.GenericEntity;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    private final ObjectMapper mapper;
    private final LicenseRepository licenseRepository;
    @Value("${kafka.license.server:127.0.0.1}")
    private String kafkaServer;
    @Value("${kafka.license.port:9092}")
    private String kafkaPort;
    @Value("${kafka.license.topicName:license}")
    private String topicName;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public ConsumerFactory<String, GenericEntity<?>> consumerFactory() {
        Map<String, Object> kafkaProps = new HashMap<>();
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer + ":" + kafkaPort);
        kafkaProps.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);

//        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        kafkaProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        kafkaProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        kafkaProps.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.tecsup.microservices.audit_service.config.KafkaConfig");
        kafkaProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.tecsup.microservices.audit_service.config.KafkaConfig");

        kafkaProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.tecsup.microservices.*");

        return new DefaultKafkaConsumerFactory<>(kafkaProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GenericEntity<?>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GenericEntity<?>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @KafkaListener(topics = "license")
    public void listenTopic(GenericEntity<?> message) {

//        log.info("=====>" + message.getT());

       if (message.getClassName().equals(DriverLicenseEntityMongo.class.getSimpleName())) {

            DriverLicenseEntityMongo driverLicenseEntityMongo = mapper.convertValue(message.getT(), new TypeReference<>() {
            });
            log.info("Crear - Actualizar License Entity: " + driverLicenseEntityMongo);
            licenseRepository.save(driverLicenseEntityMongo);

        }
        else if (message.getClassName().equals(String.class.getSimpleName())) {
            log.info("Eliminar License Entity: " + message.toString());

            // Calcular la fecha límite
            LocalDate thresholdDate = LocalDate.now().plusDays(30);
            // Crear la consulta para encontrar documentos con dueDate < thresholdDate
            Query query = new Query();
            query.addCriteria(Criteria.where("dueDate").lt(thresholdDate));

            // Definir el campo a actualizar
            Update update = new Update();
            update.set("state", false);

            // Ejecutar la actualización en múltiples documentos
            mongoTemplate.updateMulti(query, update, DriverLicenseEntityMongo.class);
        }
        else {
            log.info("..::.." + message.getClassName());
            log.info("=====>" + message.getClass());
        }
    }
}
