package com.example.demo.application.dto.out.chat;

import com.example.demo.domain.user.User;

public record TypingOutput(User user, Boolean typing) {
}