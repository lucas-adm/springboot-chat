package com.example.demo.domain.user;

import lombok.Data;

import java.util.UUID;

@Data
public class User {

    private UUID id;
    private String status;
    private String username;
    private String displayName;
    private String avatar;
    private String highlight;

}