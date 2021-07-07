package com.ahmad.studentTest.exception;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(short amount){
        super("Invalid amount = "+amount);
    }
}
