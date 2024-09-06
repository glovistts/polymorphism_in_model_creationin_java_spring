package com.polymorphisminmodelcreationinjavaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PolymorphismInModelCreationInJavaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolymorphismInModelCreationInJavaSpringApplication.class, args);
    }

}
