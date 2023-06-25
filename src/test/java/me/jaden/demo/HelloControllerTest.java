package me.jaden.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HelloControllerTest {

    @MockBean
    private HelloSerivce helloSerivce;

    @Test
    void helloControllerTest() {
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");
        assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failHelloControllerTest() {
        HelloController helloController = new HelloController(name -> name);

        assertThatThrownBy(() -> helloController.hello(null)).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> helloController.hello("")).isInstanceOf(IllegalArgumentException.class);
    }

}