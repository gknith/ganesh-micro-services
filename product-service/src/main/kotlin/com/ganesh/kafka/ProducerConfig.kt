package com.ganesh.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import java.util.HashMap

@Configuration
@EnableKafka
class ProducerConfig {

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> {
        val config = HashMap<String, Any>()
        config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] =  "tools_kafka_1:9093"
        //config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] =  "localhost:9092"
        config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java

        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }
}