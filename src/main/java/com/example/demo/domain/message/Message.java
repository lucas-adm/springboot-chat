package com.example.demo.domain.message;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class Message {

    private UUID id;
    private UUID creator;
    private String content;
    private Boolean read;
    private Boolean updated;
    private Instant createdAt;
    private Instant updatedAt;

}