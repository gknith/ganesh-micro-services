package com.ganesh.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.ganesh.model.*
import com.ganesh.service.ProductService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductMutationResolver(val service: ProductService) : GraphQLMutationResolver {
    fun registerProduct(name: String,category: String,price: Float): Product {
        return service.registerProduct(name, category, price)
    }
    fun deregisterProduct(id: UUID) : Boolean{
        return service.deregisterProduct(id)
    }

    fun renameProduct(id: UUID, name: String): Product {
        return service.renameProduct(id, name)
    }

    fun setPrice(id: UUID, price: Float): Boolean {
        return service.setPrice(id, price)
    }
    fun increasePrice(id: UUID, price: Float): Boolean {
        return service.increasePrice(id, price)
    }

    fun decreasePrice(id: UUID, price: Float): Boolean {
        return service.decreasePrice(id, price)
    }
}