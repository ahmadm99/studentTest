package com.ahmad.studentTest.exception;

public class InvalidPageSizeException extends RuntimeException{
    public InvalidPageSizeException(int pageSize){
        super("Invalid Size "+pageSize +". Size should be between 1 and 100");
    }
}
