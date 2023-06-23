package me.jaden.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class HelloDecorator implements HelloSerivce{

    private final HelloSerivce helloSerivce;

    public HelloDecorator(HelloSerivce helloSerivce) {
        this.helloSerivce = helloSerivce;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloSerivce.sayHello(name) + "*";
    }

    @Override
    public int countOf(String name) {
        return helloSerivce.countOf(name);
    }


}
