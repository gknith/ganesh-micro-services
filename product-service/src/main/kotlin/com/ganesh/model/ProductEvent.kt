package com.ganesh.model

import java.util.UUID

interface ProductEvent

data class ProductPriceSet(val id:UUID, val price:Float) : ProductEvent
data class DeregisterProduct(val id:UUID) : ProductEvent