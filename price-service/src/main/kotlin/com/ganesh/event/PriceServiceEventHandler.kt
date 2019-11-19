package com.ganesh.event

import com.ganesh.model.DeregisterProduct
import com.ganesh.model.Price
import com.ganesh.model.ProductEvent
import com.ganesh.model.ProductPriceSet
import com.ganesh.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PriceServiceEventHandler(val productService: ProductService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventListener
    fun send(price: ProductPriceSet) {
        logger.info("got price set Event: {}", price)
        productService.setProductPrice(price.id, price.price);
    }

    @EventListener
    fun send(price: DeregisterProduct) {
        logger.info("got product deregister event: {}", price)
        productService.deregisterProduct(price.id)
    }
}