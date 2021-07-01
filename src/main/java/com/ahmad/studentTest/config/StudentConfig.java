package com.ahmad.studentTest.config;

import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
          Student ahmad = new Student(
                  "Ahmad",
                  LocalDate.of(1999, Month.JUNE,7),"ahmad@gmail.com");
            Student mahdi = new Student(
                    "Mahdi",
                    LocalDate.of(1999, Month.JULY,7),"mahdi@gmail.com");
            Student waleed = new Student(
                    "Waleed",
                    LocalDate.of(2002, Month.FEBRUARY,7),"waleed@gmail.com");
            studentRepository.saveAll(Arrays.asList(ahmad,mahdi,waleed));
        };

    }
}
