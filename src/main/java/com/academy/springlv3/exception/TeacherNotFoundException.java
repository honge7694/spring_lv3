package com.academy.springlv3.exception;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
