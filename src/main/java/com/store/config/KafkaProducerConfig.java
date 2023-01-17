package com.store.config;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.config.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${kafka.config.schema-registry-url}")
    private String schemaRegistryUrl;

    @Value("${kafka.config.group-id}")
    private String groupId;

    @Bean
    public ProducerFactory<String, SpecificRecord> producerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, SpecificRecord> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
