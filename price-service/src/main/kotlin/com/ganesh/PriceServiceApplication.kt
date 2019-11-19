package com.ganesh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PriceServiceApplication

fun main(args: Array<String>) {
	runApplication<PriceServiceApplication>(*args)
}
