package com.sergl.appst.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
            Student nadia = new Student(
                    "Nadia1",
                    "nad@gmail.com",
                    LocalDate.of(2000, Month.FEBRUARY,12)
            );

            Student alexei = new Student(
                    "Alexei2",
                    "alexei@gmail.com",
                    LocalDate.of(2002, Month.MARCH,22)
            );
            repository.saveAll(List.of(alexei,nadia));
        };
    }
}