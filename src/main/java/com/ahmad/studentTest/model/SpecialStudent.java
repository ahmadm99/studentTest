package com.ahmad.studentTest.model;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class SpecialStudent extends Student {
    private Short amount;

    public SpecialStudent(@NonNull String name, @NonNull LocalDate dob, @NonNull String email, Short amount) {
        super(name, dob, email);
        this.amount = amount;
    }
}