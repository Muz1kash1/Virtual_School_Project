package com.fabit.schoolapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SchoolApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchoolApplication.class, args);
  }

}
