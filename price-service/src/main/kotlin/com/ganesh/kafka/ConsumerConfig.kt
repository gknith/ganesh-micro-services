package com.ganesh.kafka

import com.ganesh.model.Price
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

import java.util.HashMap

@Configuration
@EnableKafka
internal class ConsumerConfig {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        val config = HashMap<String, Any>()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "tools_kafka_1:9093"
        //config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        config[ConsumerConfig.GROUP_ID_CONFIG] = ""
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java

        val valueDeserializer = JsonDeserializer(Any::class.java)
        valueDeserializer.addTrustedPackages("*");
        return DefaultKafkaConsumerFactory(config, StringDeserializer(), valueDeserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
