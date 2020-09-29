package com.reka.lakatos.angularchat.controller;

import com.reka.lakatos.angularchat.document.ChatMessage;
import com.reka.lakatos.angularchat.service.AngularChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final AngularChatService chatService;

    @PostMapping("/insert-message")
    public ChatMessage insertMessage(@RequestBody ChatMessage chatMessage) {
        return chatService.insertMessage(chatMessage);
    }

    @GetMapping("/messages")
    public List<ChatMessage> getMessages() {
        return chatService.getMessagesOrderByTimeStamp();
    }
}
