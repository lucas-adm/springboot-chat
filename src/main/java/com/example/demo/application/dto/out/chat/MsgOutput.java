package com.example.demo.application.dto.out.chat;

import com.example.demo.domain.message.Message;
import com.example.demo.domain.user.User;

public record MsgOutput(User user, Message message) {
}