package com.ganesh.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Price (@Id val id:UUID, val productId:UUID, val price:Float)