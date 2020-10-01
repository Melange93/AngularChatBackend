package com.reka.lakatos.angularchat.document;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Document("ChatUser")
@Data
@ToString
public class ChatUser {
    @Id
    private String userName;
    @Indexed(unique = true)
    private String email;
    private String password;
    private Status status;
    private List<Roles> roles;

        public ChatUser(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.status = Status.OFFLINE;
        this.roles = Collections.singletonList(Roles.USER);
    }

    public List<String> getRolesInString() {
        return roles.stream()
                .map(Roles::getValue)
                .collect(Collectors.toList());
    }
}

