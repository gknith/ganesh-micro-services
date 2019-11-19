package com.ganesh.service

import com.ganesh.model.DeregisterProduct
import com.ganesh.model.Product
import com.ganesh.model.ProductPriceSet

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.event.EventListener
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

//@ExtendWith(SpringExtension::class)
//@DataJpaTest
//@EnableAutoConfiguration
//@ContextConfiguration(classes = [ProductServiceImpl::class])
//class ProductServiceEventsIntegrationTests(@Autowired val productService: ProductService) {
//
//    @MockBean
//    lateinit var eventHandler: TestEventHandler
//
//    private lateinit var product: Product
//
//    @BeforeEach
//    fun setUp() {
//        product = productService.registerProduct("Mobile", "RUPEES", 100.0F)
//    }
//
//    @Test
//    fun `Check Product registration event was triggered`() {
//        val argumentCaptor = argumentCaptor<ProductPriceSet>()
//        verify(eventHandler).handleProductPriceSet(argumentCaptor.capture())
//
//        val expected = ProductPriceSet(product.id, 100.0F)
//        assertThat(argumentCaptor.lastValue).isEqualTo(expected)
//    }
//
//    @Test
//    fun `Check Product price set event was triggered with right attributes`() {
//        productService.setPrice(product.id, 50.0F)
//
//        val argumentCaptor = argumentCaptor<ProductPriceSet>()
//        verify(eventHandler).handleProductPriceSet(argumentCaptor.capture())
//
//        val expected = ProductPriceSet(product.id, 100.0F)
//        assertThat(argumentCaptor.lastValue).isEqualTo(expected)
//    }
//}

interface TestEventHandler {
    @EventListener
    fun handleProductPriceSet(event: ProductPriceSet)

    @EventListener
    fun handleProductDeregister(event: DeregisterProduct)
}