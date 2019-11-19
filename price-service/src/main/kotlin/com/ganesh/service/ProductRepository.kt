package com.ganesh.service

import com.ganesh.model.Price
import org.springframework.data.repository.CrudRepository
import java.util.UUID
import javax.transaction.Transactional

@Transactional(Transactional.TxType.MANDATORY)
internal interface ProductRepository : CrudRepository<Price, UUID> {

    fun deleteByProductId(productId: UUID)

    fun findByProductId(productId: UUID): MutableList<Price>
}