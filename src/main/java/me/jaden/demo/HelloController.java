package me.jaden.demo;

import java.util.Objects;

public class HelloController {

    private final HelloSerivce helloSerivce;

    public HelloController(HelloSerivce helloSerivce) {
        this.helloSerivce = helloSerivce;
    }

    public String hello(String name) {
        return helloSerivce.sayHello(Objects.requireNonNull(name));
    }
}
