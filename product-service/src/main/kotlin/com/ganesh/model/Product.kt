package com.ganesh.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product (@Id val id: UUID, var name:String, var category:String, var price:Float)