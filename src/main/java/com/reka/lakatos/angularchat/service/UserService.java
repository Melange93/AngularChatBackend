package com.reka.lakatos.angularchat.service;

import com.reka.lakatos.angularchat.document.ChatUser;
import com.reka.lakatos.angularchat.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ChatUserRepository userRepository;

    public void registerNewUser(ChatUser user) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public ChatUser findUserByUserEmail(String email) {
        return userRepository.findChatUserByEmail(email);
    }
}
