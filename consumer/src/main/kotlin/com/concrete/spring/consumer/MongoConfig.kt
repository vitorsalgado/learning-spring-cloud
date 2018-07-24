package com.concrete.spring.consumer

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration

@Configuration
class MongoConfig : AbstractReactiveMongoConfiguration() {
  @Bean
  override fun reactiveMongoClient(): MongoClient = MongoClients.create()

  override fun getDatabaseName(): String {
    return "consumer"
  }
}
