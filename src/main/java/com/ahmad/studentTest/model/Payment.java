package com.ahmad.studentTest.model;

import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Payment {
    @Id
    private Long id;

    @NonNull
    private Boolean type;

    @NonNull
    private Short amount;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    @MapsId
    private Student student;
}
