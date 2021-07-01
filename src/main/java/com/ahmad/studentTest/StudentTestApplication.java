package com.ahmad.studentTest;

import com.ahmad.studentTest.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class StudentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTestApplication.class, args);
	}


}
