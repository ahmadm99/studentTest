package com.ahmad.studentTest.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class StudentRequestDTO {
    private String name;
    private LocalDate dob;
    private String email;
    private Boolean type;
}
