package com.training.project.service

import com.ganesh.model.Product
import com.ganesh.service.ProductService
import com.ganesh.service.ProductServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

//@ExtendWith(SpringExtension::class)
//@DataJpaTest
//@EnableAutoConfiguration
//@ContextConfiguration(classes = [ProductServiceImpl::class])
//class ProductServiceIntegrationTests(@Autowired val service: ProductService) {
//
//    private lateinit var product: Product
//
//    @BeforeEach
//    fun setUp() {
//        product = service.registerProduct("Pen", "PESOS", 50.0F)
//    }
//
//    @Test
//    fun `Check Product registration`() {
//        assertThat(service.getProductById(product.id)).isNotNull
//    }
//
//    @Test
//    fun `Check attributes of registered products`() {
//        assertThat(service.getProductById(product.id)).isEqualTo(product)
//    }
//
//    @Test
//    fun `Check Product registration and it exists in the products list`() {
//        assertThat(service.getProductList().contains(product)).isTrue()
//    }
//
//    @Test
//    fun `Check Product deregistration`() {
//        service.deregisterProduct(product.id)
//        assertThrows<NoSuchElementException> { service.getProductById(product.id) }
//    }
//}
