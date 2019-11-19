package com.ganesh.kafka

import com.ganesh.model.DeregisterProduct
import com.ganesh.model.ProductPriceSet
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(topics = ["productEvent"], groupId = "price-Event-consumer", containerFactory = "kafkaListenerContainerFactory")
class KotlinConsumer(private val publisher: ApplicationEventPublisher) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaHandler
    fun processMessage(productPriceSet: ProductPriceSet) {
        logger.info("got price set kafka event: {}", productPriceSet)
        publisher.publishEvent(productPriceSet);
    }

    @KafkaHandler
    fun processMessage(deregisterProduct: DeregisterProduct) {
        logger.info("got product deregister kafka event: {}", deregisterProduct)
        publisher.publishEvent(deregisterProduct);
    }
}