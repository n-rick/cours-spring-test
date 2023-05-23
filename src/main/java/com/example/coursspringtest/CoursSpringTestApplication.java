package com.example.coursspringtest;

import com.example.coursspringtest.model.Personne;
import com.example.coursspringtest.service.PersonneService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class CoursSpringTestApplication {
    private PersonneService personneService;
    public static void main(String[] args) {
        SpringApplication.run(CoursSpringTestApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner start() {
//        return args -> {
//            personneService.save(new Personne("Wick", "John", 45));
//            personneService.save(new Personne("Linus", "Benjamin", 30));
//            personneService.save(new Personne("Pradel", "Jacques", 65));
//        };
//    }
}
