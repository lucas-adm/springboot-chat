package com.example.demo.domain.message;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "messages")
@Data
public class Message {

    @Id
    private String id;
    private String creator;
    private String content;
    @CreatedDate
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean read = false;
    private Boolean updated = false;

    public static Message create(String creator, String content) {
        Message message = new Message();
        message.id = UUID.randomUUID().toString();
        message.creator = creator;
        message.content = content;
        message.read = false;
        message.updated = false;
        return message;
    }

}