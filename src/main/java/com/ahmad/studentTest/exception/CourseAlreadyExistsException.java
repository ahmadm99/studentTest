package com.ahmad.studentTest.exception;

public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException(String courseName) {
        super("Course already exists with name = "+courseName);
    }
}
