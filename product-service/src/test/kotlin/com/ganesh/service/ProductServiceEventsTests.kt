package com.training.project.service

import com.ganesh.model.DeregisterProduct
import com.ganesh.model.Product
import com.ganesh.model.ProductPriceSet
import com.ganesh.repository.ProductRepository
import com.ganesh.service.ProductService
import com.ganesh.service.ProductServiceImpl
import com.nhaarman.mockitokotlin2.KArgumentCaptor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class ProductServiceEventsTests {

    private lateinit var service: ProductService

    private val repository: ProductRepository = mock()
    private val publisher: ApplicationEventPublisher = mock()

    private val product = Product(UUID.randomUUID(), "Camera", "Price", 100.0F)

    @BeforeEach
    fun setUp() {
        service = ProductServiceImpl(repository, publisher)
    }

    @Test
    fun `Check Product registration event`() {

        whenever(repository.save(any<Product>())).thenReturn(product)
        whenever(repository.findById(any())).thenReturn(Optional.of(product))

        var product = service.registerProduct("Camera", "Camera", 100.0F)

        val expectedEvent = ProductPriceSet(product.id, product.price)
        assertThat(argumentCaptor<ProductPriceSet>().getIt()).isEqualTo(expectedEvent)
    }

    @Test
    fun `Check Product deregistration event`() {
        whenever(repository.existsById(any())).thenReturn(true)
        whenever(repository.findById(any())).thenReturn(Optional.of(product))

        service.deregisterProduct(product.id)

        val expectedEvent = DeregisterProduct(product.id)
        assertThat(argumentCaptor<DeregisterProduct>().getIt()).isEqualTo(expectedEvent)
    }

    private inline fun <reified T : Any> KArgumentCaptor<T>.getIt() = verify(publisher).publishEvent(capture()).let { lastValue }
}