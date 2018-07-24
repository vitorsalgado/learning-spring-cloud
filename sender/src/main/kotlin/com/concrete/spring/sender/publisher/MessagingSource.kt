package com.concrete.spring.sender.publisher

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface MessagingSource {
  @Output("messageChannel")
  fun sender(): MessageChannel
}
