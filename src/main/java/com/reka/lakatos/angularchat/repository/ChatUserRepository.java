package com.reka.lakatos.angularchat.repository;

import com.reka.lakatos.angularchat.document.ChatUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatUserRepository extends MongoRepository<ChatUser, String> {
    ChatUser findChatUserByEmail(String email);
}
