package com.reka.lakatos.angularchat.document;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Document("ChatUser")
@Data
@ToString
public class ChatUser {
    @Id
    private String email;
    private String userName;
    private String password;
    private List<Roles> roles;

    public ChatUser(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.roles = Collections.singletonList(Roles.USER);
    }

    public List<String> getRolesInString() {
        return roles.stream()
                .map(Roles::getValue)
                .collect(Collectors.toList());
    }
}

