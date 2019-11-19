package com.ganesh.graphql

import com.ganesh.model.Product
import com.ganesh.service.ProductService
import com.nhaarman.mockitokotlin2.any

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.NoSuchElementException
import java.util.UUID.randomUUID

class ProductQueryResolverTest {
    private lateinit var service: ProductService
    private lateinit var resolver: ProductQueryResolver

    private val productId = randomUUID()
    private val product = Product(productId, "name", "category", 5.0F)

    @BeforeEach
    fun setUp() {
        service = mock()
        resolver = ProductQueryResolver(service)
    }

    @Test
    fun `Check Product List retrieval`() {
        whenever(service.getProductList()).thenReturn(mutableListOf(product))
        resolver.getProductList().apply {
            assertThat(this).isNotEmpty()
            assertThat(this).contains(product)
        }
    }

    @Test
    fun `Check Product retrieval`() {
        whenever(service.getProductById(any())).thenReturn(product)
        resolver.getProductById(productId).apply {
            assertThat(this).isEqualTo(product)
        }
    }

    @Test
    fun `Check Product retrieval 3`() {
        whenever(service.getProductList()).thenThrow(NoSuchElementException())
        assertThrows<NoSuchElementException> { resolver.getProductList() }
    }

    @Test
    fun `Check Product`() {
        whenever(service.getProductList()).thenReturn(mutableListOf(product))
        resolver.getProductList().apply {
            assertThat(this).isNotNull()
            assertThat(this).isNotEmpty()
        }
    }
}