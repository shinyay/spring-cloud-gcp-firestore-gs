package com.google.shinyay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCloudGcpFirestoreGsApplication

fun main(args: Array<String>) {
	runApplication<SpringCloudGcpFirestoreGsApplication>(*args)
}
