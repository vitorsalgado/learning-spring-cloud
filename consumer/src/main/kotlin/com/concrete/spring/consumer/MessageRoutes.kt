package com.concrete.spring.consumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.router

@Configuration
class Routes(private val messageRepository: MessageRepository) {
  @Bean
  fun route() = router {
    GET("/health")({ _ -> ServerResponse.noContent().build() })

    GET("/message/{id}")({
      messageRepository
        .findById(it.pathVariable("id"))
        .flatMap { message -> ok().body(fromObject(message)) }
        .switchIfEmpty(notFound().build())
    })
  }
}
