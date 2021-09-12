package com.xeanco.xeanco.exception;

import lombok.Data;

@Data
public class UsernameAlreadyExistResponse {
    private String username;
    public UsernameAlreadyExistResponse(String username){
        this.username = username;
    }
}
