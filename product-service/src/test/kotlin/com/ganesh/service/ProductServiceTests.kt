package com.training.project.service

import com.ganesh.model.Product
import com.ganesh.repository.ProductRepository
import com.ganesh.service.ProductService
import com.ganesh.service.ProductServiceImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.springframework.context.ApplicationEventPublisher
import java.util.Optional
import java.util.UUID

class ProductServiceTests {

    private lateinit var service: ProductService

    private var repository: ProductRepository = mock()
    private var publisher: ApplicationEventPublisher = mock()
    private val expectedProduct = Product(UUID.randomUUID(), "Pen", "Pen", 100.0F)

    @BeforeEach
    fun setUp() {
        service = ProductServiceImpl(repository, publisher)
    }

    @Test
    fun `Check Product is retrieved`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        val product = service.getProductById(expectedProduct.id)

        assertThat(product).isNotNull
        assertThat(product).isEqualTo(expectedProduct)
    }

    @Test
    fun `Check Product is retrieved failure throws exception`() {
        whenever(repository.findById(any())).thenThrow(NoSuchElementException())
        assertThrows<NoSuchElementException> { service.getProductById(UUID.randomUUID()) }
    }

    @Test
    fun `Check Products are retrieved in a list`() {
        whenever(repository.findAll()).thenReturn(listOf(expectedProduct))
        val products = service.getProductList()

        assertThat(products).isNotEmpty
        assertThat(products).contains(expectedProduct)
    }

    @Test
    fun `Check Product registration`() {
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())
        val product = service.registerProduct("Pen", "Pen", 100.0F)

        product.apply {
            assertThat(name).isEqualTo(expectedProduct.name)
            assertThat(price).isEqualTo(expectedProduct.price)
        }
    }

    @Test
    fun `Check Product deregistration`() {
        whenever(repository.deleteById(any())).thenThrow(IllegalArgumentException())
        assertThrows<IllegalArgumentException> { service.deregisterProduct(UUID.randomUUID()) }
    }

    @Test
    fun `Check Product set price`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        service.setPrice(expectedProduct.id,100.0F)
        assertThat(service.getProductById(expectedProduct.id)?.price).isEqualTo(expectedProduct.price)
    }

    @Test
    fun `Check Product set price cannot be assigned negative value`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        service.setPrice(expectedProduct.id,50.0F)
        assertThrows<IllegalArgumentException> { service.setPrice(expectedProduct.id, -100.0F) }
    }

    @Test
    fun `Check Product increase price`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        service.increasePrice(expectedProduct.id, 50.0F)
        val expectedChangedProduct = Product(expectedProduct.id, "Pen", "Pen", 150.0F)
        assertThat(service.getProductById(expectedProduct.id)?.price).isEqualTo(expectedChangedProduct.price)
    }

    @Test
    fun `Check Product increase price cannot be provided negative or zero percentage`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        assertThrows<IllegalArgumentException> { service.increasePrice(expectedProduct.id, -100.0F) }
    }

    @Test
    fun `Check Product decrease price`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        whenever(repository.getOne(any())).thenReturn(expectedProduct)
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        val expectedChangedProduct = Product(expectedProduct.id, "Pen", "Pen",50.0F)
        service.decreasePrice(expectedProduct.id, 50.0F)
        assertThat(service.getProductById(expectedProduct.id)?.price).isEqualTo(expectedChangedProduct.price)
    }

    @Test
    fun `Check Product decrease price cannot be provided negative or zero percentage`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        assertThrows<IllegalArgumentException> { service.decreasePrice(expectedProduct.id, 100.0F) }
    }

    @Test
    fun `Check Product rename`() {
        whenever(repository.findById(any())).thenReturn(Optional.of(expectedProduct))
        whenever(repository.getOne(any())).thenReturn(expectedProduct)
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        val expectedChangedProduct = Product(expectedProduct.id, "Mouse", "Mouse",100.0F)
        service.renameProduct(expectedProduct.id, "Mouse")
        assertThat(service.getProductById(expectedProduct.id)?.name).isEqualTo(expectedChangedProduct.name)
    }

    @Test
    fun `Check Product rename cannot have empty value`() {
        whenever(repository.getOne(any())).thenReturn(expectedProduct)
        whenever(repository.save(any<Product>())).then(returnsFirstArg<Product>())

        assertThrows<IllegalArgumentException> { service.renameProduct(expectedProduct.id, "") }
    }
}