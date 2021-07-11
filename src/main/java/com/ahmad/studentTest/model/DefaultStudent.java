package com.ahmad.studentTest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("Default")
public class DefaultStudent extends Student{
    private Short amount;

    public DefaultStudent(@NonNull String name, @NonNull LocalDate dob, @NonNull String email, Short amount) {
        super(name, dob, email);
        this.amount = amount;
    }
}
