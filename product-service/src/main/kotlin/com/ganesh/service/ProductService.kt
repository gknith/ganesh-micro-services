package com.ganesh.service

import com.ganesh.model.Product
import java.util.UUID

interface ProductService {
    fun registerProduct(name: String,category: String,price: Float): Product

    fun deregisterProduct(id: UUID):Boolean

    fun renameProduct(id: UUID, name: String): Product

    fun setPrice(id: UUID, price: Float): Boolean

    fun increasePrice(id: UUID, price: Float): Boolean

    fun decreasePrice(id: UUID, price: Float): Boolean

    fun getProductById(id: UUID): Product?

    fun getProductList(): MutableList<Product>
}