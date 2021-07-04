package com.ahmad.studentTest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;
    @NonNull
    private String email;
    @Transient
    @Getter(AccessLevel.NONE)
    private Integer age;

    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

//make the relationship bidirectional --future


}

