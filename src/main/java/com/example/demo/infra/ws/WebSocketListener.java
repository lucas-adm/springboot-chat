package com.example.demo.infra.ws;

import com.example.demo.application.dtos.in.chat.PresenceInput;
import com.example.demo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class WebSocketListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;
    private final Set<String> onlineIds = ConcurrentHashMap.newKeySet();
    private final Map<String, User> sessions = new ConcurrentHashMap<>();

    @EventListener
    public void handleConnect(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();
        String uJson = sha.getFirstNativeHeader("user");
        if (uJson == null || uJson.isBlank()) return;
        User user = objectMapper.readValue(uJson, User.class);
        sessions.put(sessionId, user);
        onlineIds.add(user.getId());
        messagingTemplate.convertAndSend("/topics/presence", new PresenceInput(user, true));
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        User user = sessions.remove(sessionId);
        if (user == null) return;
        onlineIds.remove(user.getId());
        messagingTemplate.convertAndSend("/topics/presence", new PresenceInput(user, false));
    }

}