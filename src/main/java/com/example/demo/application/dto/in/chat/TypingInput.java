package com.example.demo.application.dto.in.chat;

import com.example.demo.domain.user.User;

public record TypingInput(User user, Boolean typing) {
}