package com.reka.lakatos.angularchat.document;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@Document("ChatMessage")
@Data
@ToString
public class ChatMessage {
    @Id
    private String id;
    private String userName;
    private String message;
    private String timeStamp;
}
