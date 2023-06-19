package me.jaden.demo;

import org.springframework.stereotype.Service;

@Service
public class SimpleHelloService implements HelloSerivce {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
