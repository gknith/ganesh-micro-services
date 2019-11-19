package com.ganesh.repository

import com.ganesh.model.Product
import com.ganesh.repository.ProductRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import java.util.UUID


@RunWith(SpringRunner::class)
@DataJpaTest
class UserEntityRepositoryTest {

    @Autowired
    internal var productRepository: ProductRepository? = null

    @Test
    fun testSave() {
        productRepository!!.save(Product(UUID.randomUUID(), "name", "sd", 5.0F))
    }

    @Test
    fun testSave2() {
        productRepository!!.save(Product(UUID.randomUUID(), "name", "sd", 5.0F))
    }
}
