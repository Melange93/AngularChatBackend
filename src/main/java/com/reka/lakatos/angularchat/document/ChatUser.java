package com.reka.lakatos.angularchat.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Slf4j
@Document("ChatUser")
@Data
@ToString
public class ChatUser {
    @Id
    private String id;
    private String email;
    private String userName;
    private String password;
    private String status;

    public ChatUser(String email, String userName, String password, String status) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }
}

