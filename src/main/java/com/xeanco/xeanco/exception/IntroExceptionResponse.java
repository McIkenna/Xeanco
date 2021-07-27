package com.xeanco.xeanco.exception;

import lombok.Data;

@Data
public class IntroExceptionResponse {
    private String introName;

    public IntroExceptionResponse(String introName){
        this.introName = introName;
    }
}
