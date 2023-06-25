package me.jaden.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloServiceCountTest {

    @Autowired
    HelloSerivce helloSerivce;
    @Autowired
    HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseCount() {
        helloSerivce.sayHello("Toby");
        assertThat(helloRepository.countOf("Toby")).isEqualTo(1);

        helloSerivce.sayHello("Toby");
        assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
    }
}
