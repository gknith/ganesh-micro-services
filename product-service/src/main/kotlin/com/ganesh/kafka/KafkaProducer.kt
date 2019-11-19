package com.ganesh.kafka

import com.ganesh.model.ProductEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {
    var topic = "productEvent"

    @TransactionalEventListener
    fun send(productEvent: ProductEvent) {
        kafkaTemplate.send(topic, productEvent)
    }
}