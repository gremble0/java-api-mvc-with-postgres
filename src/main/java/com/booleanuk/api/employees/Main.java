package com.booleanuk.api.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    System.out.println("before main");
    SpringApplication.run(Main.class, args);
    System.out.println("after main");
  }
}
