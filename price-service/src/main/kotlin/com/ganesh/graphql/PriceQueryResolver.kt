package com.ganesh.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.ganesh.model.Price
import com.ganesh.service.ProductService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PriceQueryResolver(val productService: ProductService): GraphQLQueryResolver {

    fun getPriceHistory(productId: UUID) = productService.getPriceList(productId)
}