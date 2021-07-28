package com.ahmad.studentTest.exception;

public class ExceededMaxPageNumberException extends RuntimeException{
    public ExceededMaxPageNumberException(int pageNumber, int pageSize){
        super("Page " +pageNumber+ " exceeds maximum page number "+pageSize);
    }
}
