package com.concrete.spring.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsumerApp

fun main(args: Array<String>) {
  runApplication<ConsumerApp>(*args)
}
