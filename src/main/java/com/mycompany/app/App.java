package com.mycompany.app;

public class App {

    public String sayHello() {
        return "Hello from Maven Project!";
    }

    public static void main(String[] args) {
        System.out.println(new App().sayHello());
    }
}
