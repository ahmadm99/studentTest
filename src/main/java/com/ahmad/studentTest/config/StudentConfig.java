package com.ahmad.studentTest.config;

import com.ahmad.studentTest.model.DefaultStudent;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            List<SpecialStudent> list = new ArrayList<>();
            for(int i = 1; i<1000;i++) {
                SpecialStudent demo = new SpecialStudent("Test", LocalDate.of(2000, Month.FEBRUARY,7),"test@gmail.com");
                list.add(demo);
            }
            studentRepository.saveAll(list);

            //create 10 thousand users

        };

    }
}
