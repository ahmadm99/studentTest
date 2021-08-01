package com.ahmad.studentTest.config;

import com.ahmad.studentTest.model.Course;
import com.ahmad.studentTest.model.DefaultStudent;
import com.ahmad.studentTest.model.SpecialStudent;
import com.ahmad.studentTest.repository.CourseRepository;
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
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository){
        return args -> {
          DefaultStudent ahmad = new DefaultStudent("Ahmad", LocalDate.of(1999, Month.JUNE,7),"ahmad@gmail.com");
          SpecialStudent sAhmad = new SpecialStudent("Ahmad", LocalDate.of(1999, Month.JUNE,7),"ahmad@gmail.com");
          DefaultStudent mahdi = new DefaultStudent("Mahdi", LocalDate.of(1999, Month.JULY,7),"mahdi@gmail.com");
          SpecialStudent waleed = new SpecialStudent("Waleed", LocalDate.of(2002, Month.FEBRUARY,7),"waleed@gmail.com");

            studentRepository.saveAll(Arrays.asList(ahmad,mahdi,waleed,sAhmad));

            List<SpecialStudent> list = new ArrayList<>();
            for(int i = 1; i<100;i++) {
                SpecialStudent demo = new SpecialStudent("Test", LocalDate.of(2000, Month.FEBRUARY,7),"test@gmail.com");
                list.add(demo);
            }
            studentRepository.saveAll(list);

            Course course1 = new Course("Machine Learning");
            Course course2 = new Course("Database Systems");
            Course course3 = new Course("Web Basics");
            Course course4 = new Course("Data Structure");
            Course course5 = new Course("Design Verification");
            Course course6 = new Course("Digital Design");

            courseRepository.saveAll(Arrays.asList(course1,course2,course3,course4,course5,course6));

            ahmad.getCourses().addAll(Arrays.asList(course1,course2,course3));

            studentRepository.save(ahmad);

            //create 10 thousand users

        };

    }
}
