package com.ahmad.studentTest.model;

import org.springframework.stereotype.Component;


public abstract class BaseStudentFactory {
    public abstract Student createStudent(Boolean type);

}
