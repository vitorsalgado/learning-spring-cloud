package com.concrete.spring.sender

import com.concrete.spring.sender.handler.MessageHandler
import com.concrete.spring.sender.publisher.MessagingSource
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.util.LinkedMultiValueMap

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [(SenderApp::class), (MessageHandler::class)])
@ContextConfiguration(classes = [(SenderApp::class)])
@DirtiesContext
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SenderAppTest {
  @Autowired
  private lateinit var restTemplate: TestRestTemplate
  @Autowired
  private lateinit var messageCollector: MessageCollector
  @Autowired
  private lateinit var messagingSource: MessagingSource

  @Test
  @DisplayName("it should return 204(No Content) when calling /health ensuring application is UP")
  fun testHealthCheck() {
    val response = restTemplate.getForEntity("/health", Map::class.java)

    assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
  }

  @Test
  @DisplayName("it should add message to queue after a success POST on /message endpoint")
  fun testMessagePost() {
    val json = "{\"title\":\"TEST_TITLE\",\"actualMessage\":\"THE_MESSAGE\"}"
    val response = restTemplate.postForEntity(
      "/message",
      HttpEntity(json,
        LinkedMultiValueMap<String, String>(mutableMapOf("Content-Type" to listOf("application/json")))),
      Map::class.java)

    val message = messageCollector.forChannel(messagingSource.sender()).poll()
    val payload = message.payload as String

    assertEquals(HttpStatus.ACCEPTED, response.statusCode)
    assertThat(payload, containsString("THE_MESSAGE"))
    assertThat(payload, containsString("TEST_TITLE"))
  }
}
