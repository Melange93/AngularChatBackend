package com.reka.lakatos.angularchat.repository;

import com.reka.lakatos.angularchat.document.ChatUser;
import com.reka.lakatos.angularchat.document.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatUserRepository extends MongoRepository<ChatUser, String> {

    List<ChatUser> findAllByStatusLike(Status status);
}
