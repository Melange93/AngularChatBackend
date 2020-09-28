package com.reka.lakatos.angularchat.document;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Slf4j
@Document("ChatMessage")
@Data
@ToString
public class ChatMessage {
    @Id
    private String id;
    private String email;
    private String userName;
    private String message;
    private String timeStamp;

    public ChatMessage(String email, String userName, String message, String timeStamp) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.userName = userName;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
