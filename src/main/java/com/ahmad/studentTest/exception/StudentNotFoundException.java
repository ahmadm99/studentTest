package com.ahmad.studentTest.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("No student found with id = "+ id);
    }
}
