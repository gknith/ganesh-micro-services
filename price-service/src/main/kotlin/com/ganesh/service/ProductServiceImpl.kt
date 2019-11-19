package com.ganesh.service

import com.ganesh.model.*
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
internal class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {
    override fun setProductPrice(productId: UUID ,price: Float): Price {
        var product = Price(UUID.randomUUID(), productId, price)
        productRepository.save(product)
        return product
    }

    override fun deregisterProduct(productId: UUID): Boolean {
        productRepository.deleteByProductId(productId)
        return true
    }

    override fun getPriceList(productId: UUID): MutableList<Price> {
        return productRepository.findByProductId(productId)
    }
}