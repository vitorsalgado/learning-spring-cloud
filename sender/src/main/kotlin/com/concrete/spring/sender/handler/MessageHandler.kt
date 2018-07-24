package com.concrete.spring.sender.handler

import com.concrete.spring.sender.publisher.Message
import com.concrete.spring.sender.publisher.MessagingSource
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.integration.support.MessageBuilder.withPayload
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.accepted
import reactor.core.publisher.Mono

@Component
@EnableBinding(MessagingSource::class)
class MessageHandler(val messagingSource: MessagingSource) {
  fun postMessage(request: ServerRequest): Mono<ServerResponse> =
    request
      .bodyToMono(MessageDto::class.java)
      .map { dto -> Message(dto.title, dto.actualMessage) }
      .flatMap { message ->
        Mono.just(
          Pair(
            messagingSource
              .sender()
              .send(withPayload(message).build()),
            message))
      }
      .flatMap { accepted().body(fromObject(MessageSentDto(it.second.id))) }
}
