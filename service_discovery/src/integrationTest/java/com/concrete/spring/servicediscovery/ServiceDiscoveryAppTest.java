package com.concrete.spring.servicediscovery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ServiceDiscoveryApp.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServiceDiscoveryAppTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("it should return 200(OK) and status \"UP\" on application health check")
  void testApplicationHealth() {
    ResponseEntity<Map> response = restTemplate
      .getForEntity("/actuator/health", Map.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(response.getBody().get("status"), "UP");
  }

  @Test
  @DisplayName("it should return an HTML page when calling root of service discovery application")
  void testDashboardPage() {
    ResponseEntity<String> response = restTemplate
      .getForEntity("/", String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getHeaders().getContentType().toString().contains(MediaType.TEXT_HTML_VALUE));
  }
}
