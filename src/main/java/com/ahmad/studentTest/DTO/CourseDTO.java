package com.ahmad.studentTest.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class CourseDTO {
    private Long id;
    private String name;

    public CourseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
