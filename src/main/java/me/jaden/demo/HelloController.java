package me.jaden.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController {

    private final HelloSerivce helloSerivce;

    public HelloController(HelloSerivce helloSerivce) {
        this.helloSerivce = helloSerivce;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return helloSerivce.sayHello(Objects.requireNonNull(name));
    }
}
