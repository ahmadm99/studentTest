package com.ahmad.studentTest.config;

import com.ahmad.studentTest.model.DefaultStudent;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
          DefaultStudent ahmad = new DefaultStudent("Ahmad", LocalDate.of(1999, Month.JUNE,7),"ahmad@gmail.com");
          SpecialStudent sAhmad = new SpecialStudent("Ahmad", LocalDate.of(1999, Month.JUNE,7),"ahmad@gmail.com");
          DefaultStudent mahdi = new DefaultStudent("Mahdi", LocalDate.of(1999, Month.JULY,7),"mahdi@gmail.com");
          SpecialStudent waleed = new SpecialStudent("Waleed", LocalDate.of(2002, Month.FEBRUARY,7),"waleed@gmail.com");


            studentRepository.saveAll(Arrays.asList(ahmad,mahdi,waleed,sAhmad));
        };

    }
}
