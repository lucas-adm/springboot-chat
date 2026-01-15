package com.example.demo.application.controller.chat;

import com.example.demo.application.dto.in.chat.MsgDltInput;
import com.example.demo.application.dto.in.chat.MsgInput;
import com.example.demo.application.dto.in.chat.MsgUpdtInput;
import com.example.demo.application.dto.in.chat.TypingInput;
import com.example.demo.application.dto.out.chat.MsgDltOutput;
import com.example.demo.application.dto.out.chat.MsgOutput;
import com.example.demo.application.dto.out.chat.MsgUpdtOutput;
import com.example.demo.application.dto.out.chat.TypingOutput;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
public class ChatController {

    @MessageMapping("/typing")
    @SendTo("/topics/typing")
    public TypingOutput typed(TypingInput input) {
        return new TypingOutput(input.user(), input.typing());
    }

    @MessageMapping("/msg")
    @SendTo("/topics/msg")
    public MsgOutput create(MsgInput input) {
        return new MsgOutput(input.user(), input.message());
    }

    @MessageMapping("/read")
    @SendTo("/topics/read")
    public MsgUpdtOutput read(MsgUpdtInput input) {
        input.message().setRead(true);
        return new MsgUpdtOutput(input.message());
    }

    @MessageMapping("/update")
    @SendTo("/topics/update")
    public MsgUpdtOutput updated(MsgUpdtInput input) {
        input.message().setUpdatedAt(Instant.now());
        input.message().setUpdated(true);
        return new MsgUpdtOutput(input.message());
    }

    @MessageMapping("/delete")
    @SendTo("/topics/delete")
    public MsgDltOutput deleted(MsgDltInput input) {
        return new MsgDltOutput(input.message());
    }

}