package com.ganesh.service

import com.ganesh.model.Price
import java.util.UUID

interface ProductService {
    fun setProductPrice(productId: UUID ,price: Float): Price

    fun deregisterProduct(productId: UUID): Boolean

    fun getPriceList(productId: UUID): MutableList<Price>
}