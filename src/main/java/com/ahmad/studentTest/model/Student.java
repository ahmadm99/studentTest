package com.ahmad.studentTest.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Inheritance
@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@DiscriminatorColumn(name="Student_Type", discriminatorType = DiscriminatorType.STRING)
public abstract class Student {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
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

    public abstract void setAmount(Short amount);

    public abstract void setAmount();

    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }



}





//pagination + sorting