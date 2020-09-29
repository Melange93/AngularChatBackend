package com.reka.lakatos.angularchat.service;

import com.reka.lakatos.angularchat.document.ChatMessage;
import com.reka.lakatos.angularchat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AngularChatService {

    private final ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getMessagesOrderByTimeStamp() {
        return chatMessageRepository.findAll(
                Sort.by(Sort.Direction.ASC, "timeStamp")
        );
    }

    public ChatMessage insertMessage(ChatMessage chatMessage) {
        return chatMessageRepository.insert(chatMessage);
    }
}
