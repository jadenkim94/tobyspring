package me.jaden.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@TobyBootTest
public class HelloRepositoryTest {

    @Autowired
    HelloRepository helloRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void findHelloFailed() {
        assertThat(helloRepository.findHello("Toby")).isNull();
    }

    @Test
    void increaseCount() {
        assertThat(helloRepository.countOf("Toby")).isEqualTo(0);
        helloRepository.increaseCount("Toby");
        assertThat(helloRepository.countOf("Toby")).isEqualTo(1);
        helloRepository.increaseCount("Toby");
        assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
    }
}
