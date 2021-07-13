package com.ahmad.studentTest.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("Default")
public class DefaultStudent extends Student{
    @Setter(AccessLevel.NONE)
    private Short amount;

    public DefaultStudent(@NonNull String name, @NonNull LocalDate dob, @NonNull String email) {
        super(name, dob, email);
        this.amount = 1000;
    }

    public void setAmount(Short amount) {
        this.amount = amount;
    }
}
