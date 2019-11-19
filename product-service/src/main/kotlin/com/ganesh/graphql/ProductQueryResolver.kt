package com.ganesh.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.ganesh.model.Product
import com.ganesh.service.ProductService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductQueryResolver(val service: ProductService) : GraphQLQueryResolver {
    fun getProductById(id: UUID): Product? {
        var product = service.getProductById(id)
        return product;
    }

    fun getProductList(): MutableList<Product> {
        return service.getProductList()
    }
}