package com.concrete.spring.sender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
class SenderApp

fun main(args: Array<String>) {
  runApplication<SenderApp>(*args)
}
