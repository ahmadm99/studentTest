package com.ahmad.studentTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    private Long id;

    @NonNull
    private String name;

    //    @JsonBackReference
    @JsonIgnoreProperties("courses")
    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    Set<Student> students = new HashSet<>();

}
