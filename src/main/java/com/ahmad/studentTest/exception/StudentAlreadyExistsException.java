package com.ahmad.studentTest.exception;

public class StudentAlreadyExistsException extends  RuntimeException{
    public StudentAlreadyExistsException(String email){
        super("Student already exists with email = "+email);
    }
}
