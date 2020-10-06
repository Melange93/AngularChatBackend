package com.reka.lakatos.angularchat.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reka.lakatos.angularchat.document.ChatMessage;
import com.reka.lakatos.angularchat.service.AngularChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> webSocketSessions = new ArrayList<>();
    private final AngularChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
        chatService.insertMessage(chatMessage);
        for (WebSocketSession webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
