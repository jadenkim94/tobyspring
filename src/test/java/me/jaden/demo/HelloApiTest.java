package me.jaden.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response =
                rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // status code 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header 의 contentType 검증
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body 검증
        assertThat(response.getBody()).isEqualTo("*Hello Spring*");
    }
}
