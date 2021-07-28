package com.ahmad.studentTest.exception;

public class ExceededMaxPageSizeException extends RuntimeException{
    public ExceededMaxPageSizeException(int pageSize){
        super("Size "+ pageSize + " exceeds maximum size of 100");
    }
}
