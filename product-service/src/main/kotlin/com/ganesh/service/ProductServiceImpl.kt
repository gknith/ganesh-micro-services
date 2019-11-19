package com.ganesh.service

import com.ganesh.model.DeregisterProduct
import com.ganesh.model.ProductPriceSet
import com.ganesh.model.*
import com.ganesh.repository.ProductRepository
import com.google.common.base.Preconditions
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class ProductServiceImpl(val productRepository: ProductRepository, val publisher: ApplicationEventPublisher) : ProductService {
    override fun registerProduct(name: String,category: String,price: Float): Product {
        var product = Product(UUID.randomUUID(), name, category, price)
        productRepository.save(product);
        publisher.publishEvent(ProductPriceSet(product.id, product.price));
        return product
    }

    override fun deregisterProduct(id: UUID) : Boolean {
        productRepository.deleteById(id)
        publisher.publishEvent(DeregisterProduct(id))
        return true
    }

    override fun renameProduct(id: UUID, name: String): Product {
        Preconditions.checkArgument(name.isNotEmpty())
        var product = productRepository.getOne(id)
        product.name = name;
        productRepository.save(product)
        return product
    }

    override fun setPrice(id: UUID, price: Float): Boolean {
        Preconditions.checkArgument(price > 0)
        var product = productRepository.findById(id).get();
        product.price = price;
        productRepository.save(product)
        publisher.publishEvent(ProductPriceSet(id, product.price))
        return true
    }
    override fun increasePrice(id: UUID, price: Float): Boolean {
        var product = productRepository.findById(id).get();
        product.price = product.price + price
        Preconditions.checkArgument(product.price > 0)
        productRepository.save(product)
        publisher.publishEvent(ProductPriceSet(id, product.price))
        return true
    }

    override fun decreasePrice(id: UUID, price: Float): Boolean {
        var product = productRepository.findById(id).get();
        product.price = product.price - price
        Preconditions.checkArgument(product.price > 0)
        productRepository.save(product)
        publisher.publishEvent(ProductPriceSet(id, product.price))
        return true
    }

    override fun getProductById(id: UUID): Product? {
        return productRepository.findById(id).get()
    }

    override fun getProductList(): MutableList<Product> {
        return productRepository.findAll()
    }
}