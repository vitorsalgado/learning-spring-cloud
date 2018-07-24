package com.concrete.spring.consumer

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Message(@Id val id: String, val title: String, val actualMessage: String) {
  override fun toString(): String = "Message -> [ID: $id] [title: $title] [message: $actualMessage]"
}
