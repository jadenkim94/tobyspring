package me.jaden.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
@Component
public class HelloController {

    private final HelloSerivce helloSerivce;

    public HelloController(HelloSerivce helloSerivce) {
        this.helloSerivce = helloSerivce;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return helloSerivce.sayHello(Objects.requireNonNull(name));
    }
}
