package com.ganesh.repository

import com.ganesh.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository : JpaRepository<Product, UUID>