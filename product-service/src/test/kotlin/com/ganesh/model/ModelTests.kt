package com.ganesh.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class ModelTests {
    private val expectedProductId = randomUUID()
    private val product = Product(expectedProductId, "name", "category", 5.0F)

    @Test
    fun `Check Price`() {
        Product(expectedProductId, "name", "category", 5.0F).apply {
            assertThat(expectedProductId).isEqualTo(expectedProductId)
            assertThat(name).isEqualTo("name")
            assertThat(category).isEqualTo("category")
            assertThat(price).isEqualTo(5.0F)
        }
    }
}