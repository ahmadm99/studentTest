package com.ahmad.studentTest.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
public class StudentPaymentDTO {
    private String name;
    private LocalDate dob;
    private String email;
    private Boolean type;
    private Short amount;
}
