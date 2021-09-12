package com.xeanco.xeanco.model;

import lombok.Data;

@Data
public class InvalidResponse {
    private String username;
    private String password;

    public InvalidResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }
}
