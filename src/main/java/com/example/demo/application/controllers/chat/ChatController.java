package com.example.demo.application.controllers.chat;

import com.example.demo.application.dtos.in.chat.*;
import com.example.demo.application.dtos.out.chat.TypingOutput;
import com.example.demo.application.dtos.out.message.MessageOutput;
import com.example.demo.domain.message.Message;
import com.example.demo.domain.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MessageService service;

    @MessageMapping("/typing")
    @SendTo("/topics/typing")
    public TypingOutput type(TypingInput input) {
        return new TypingOutput(input.user(), input.typing());
    }

    @MessageMapping("/msg")
    @SendTo("/topics/msg")
    public MessageOutput create(MessageInput input) {
        return service.create(input.user(), input.content());
    }

    @MessageMapping("/read")
    @SendTo("/topics/read")
    public Message read(ReadInput input) {
        return service.read(input.id());
    }

    @MessageMapping("/update")
    @SendTo("/topics/update")
    public Message updated(UpdateInput input) {
        return service.update(input.id(), input.content());
    }

    @MessageMapping("/delete")
    @SendTo("/topics/delete")
    public Message deleted(DeleteInput input) {
        return service.delete(input.id());
    }

}