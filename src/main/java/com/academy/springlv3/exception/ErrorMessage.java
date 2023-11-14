package com.academy.springlv3.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private HttpStatus error;
    private int status;

    public ErrorMessage(String message, HttpStatus error, int status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }
}
