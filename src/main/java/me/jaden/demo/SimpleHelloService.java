package me.jaden.demo;

public class SimpleHelloService implements HelloSerivce {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
