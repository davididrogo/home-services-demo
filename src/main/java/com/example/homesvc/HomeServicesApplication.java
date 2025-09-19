package com.example.homesvc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HomeServicesApplication {
  public static void main(String[] args){
    SpringApplication.run(HomeServicesApplication.class, args);
  }
}
