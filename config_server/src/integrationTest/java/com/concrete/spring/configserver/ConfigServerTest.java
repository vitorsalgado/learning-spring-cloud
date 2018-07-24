package com.concrete.spring.configserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ConfigServerApp.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConfigServerTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("it should return 200(OK) and \"UP\" on health check status")
  void test() {
    ResponseEntity<Map> response = restTemplate.getForEntity("/actuator/health", Map.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response.getBody().get("status"), "UP");
  }
}
