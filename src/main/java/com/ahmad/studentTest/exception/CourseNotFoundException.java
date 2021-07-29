package com.ahmad.studentTest.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(Long courseId){
        super("No course found with id "+courseId);
    }
}
