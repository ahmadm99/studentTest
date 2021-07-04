package com.ahmad.studentTest.config;

import com.ahmad.studentTest.model.Payment;
import com.ahmad.studentTest.model.Student;
import com.ahmad.studentTest.repository.PaymentRepository;
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
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
        return args -> {
          Student ahmad = new Student(
                  "Ahmad",
                  LocalDate.of(1999, Month.JUNE,7),"ahmad@gmail.com");
          Payment ahmadPayment = new Payment(true,(short)500);
//          ahmad.setPayment(ahmadPayment);
          ahmadPayment.setStudent(ahmad);

            Student mahdi = new Student(
                    "Mahdi",
                    LocalDate.of(1999, Month.JULY,7),"mahdi@gmail.com");
            Payment mahdiPayment = new Payment(false,(short)1000);
//            mahdi.setPayment(mahdiPayment);
            mahdiPayment.setStudent(mahdi);

            Student waleed = new Student(
                    "Waleed",
                    LocalDate.of(2002, Month.FEBRUARY,7),"waleed@gmail.com");
            Payment waleedPayment = new Payment(true,(short)500);
//            waleed.setPayment(waleedPayment);
            waleedPayment.setStudent(waleed);
            paymentRepository.saveAll(Arrays.asList(ahmadPayment,mahdiPayment,waleedPayment));
            studentRepository.saveAll(Arrays.asList(ahmad,mahdi,waleed));
        };

    }
}
