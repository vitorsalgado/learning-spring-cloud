package com.concrete.spring.sender

import com.concrete.spring.sender.handler.MessageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse.noContent
import org.springframework.web.reactive.function.server.router

@Configuration
class Routes(private val messageHandler: MessageHandler) {
  @Bean
  fun route() = router {
    GET("/health")({ _ -> noContent().build() })
    POST("/message")(messageHandler::postMessage)
  }
}
