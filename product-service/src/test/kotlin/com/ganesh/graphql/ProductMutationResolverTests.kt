package com.training.project.graphql

import com.ganesh.graphql.ProductMutationResolver
import com.ganesh.model.Product
import com.ganesh.service.ProductService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class ProductMutationResolverTests {

    private lateinit var service: ProductService
    private lateinit var resolver: ProductMutationResolver

    private val productId = UUID.randomUUID()
    private val productModel = Product(productId, "name", "category", 5.0F)

    @BeforeEach
    fun setUp() {
        service = mock()
        resolver = ProductMutationResolver(service)
    }

    @Test
    fun `Check registerProduct result wrapping`() {
        whenever(service.registerProduct(any(), any(), any())).thenReturn(productModel)
        resolver.registerProduct("name", "category", 5.0F).apply {
            assertThat(this).isEqualTo(productModel)
        }
    }

    @Test
    fun `Check deregisterProduct result wrapping`() {
        whenever(service.deregisterProduct(any())).thenReturn(true)
        resolver.deregisterProduct(productModel.id).apply {
            assertThat(this).isEqualTo(true)
        }
    }

    @Test
    fun `Check deregisterProduct exception propagation`() {
        whenever(service.deregisterProduct(any())).thenThrow(NoSuchElementException())
        assertThrows<NoSuchElementException> { resolver.deregisterProduct(UUID.randomUUID())}
    }

    @Test
    fun `Check Product increasePrice result wrapping`() {
        whenever(service.increasePrice(any(), any())).thenReturn(true)
        resolver.increasePrice(productModel.id, 50.0F).apply {
            assertThat(this).isEqualTo(true)
        }
    }

    @Test
    fun `Check Product decreasePrice result wrapping`() {
        whenever(service.decreasePrice(any(), any())).thenReturn(true)
        resolver.decreasePrice(productModel.id, 50.0F).apply {
            assertThat(this).isEqualTo(true)
        }
    }

    @Test
    fun `Check Product setPrice result wrapping`() {
        whenever(service.setPrice(any(), any())).thenReturn(true)
        resolver.setPrice(productModel.id, 50.0F).apply {
            assertThat(this).isEqualTo(true)
        }
    }

    @Test
    fun `Check Product renameProduct result wrapping`() {
        whenever(service.renameProduct(any(), any())).thenReturn(productModel)
        resolver.renameProduct(productModel.id, "Fanta").apply {
            assertThat(this).isEqualTo(productModel)
        }
    }
}