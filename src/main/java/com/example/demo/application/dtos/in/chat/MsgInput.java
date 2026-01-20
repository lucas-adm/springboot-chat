package com.example.demo.application.dtos.in.chat;

import com.example.demo.domain.message.Message;
import com.example.demo.domain.user.User;

public record MsgInput(User user, Message message) {
}