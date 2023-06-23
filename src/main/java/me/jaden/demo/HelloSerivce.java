package me.jaden.demo;

public interface HelloSerivce {
    String sayHello(String name);

    default int countOf(String name) {
        return 0;
    }
}
