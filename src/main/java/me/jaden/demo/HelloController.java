package me.jaden.demo;

import org.apache.logging.log4j.util.Strings;
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
        if(Strings.isBlank(name)) {
            throw new IllegalArgumentException();
        }
        return helloSerivce.sayHello(name);
    }
}
