package com.concrete.spring.sender.publisher

import java.util.UUID

data class Message(val title: String, val actualMessage: String) {
  val id: String = UUID.randomUUID().toString()
}
