package com.concrete.spring.consumer

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink

@EnableBinding(Sink::class)
class MessageListener(private val messageRepository: MessageRepository) {
  @StreamListener(Sink.INPUT)
  fun handle(message: Message) {
    System.out.println(message)
    messageRepository.save(message)
  }
}
