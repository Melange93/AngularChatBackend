package com.reka.lakatos.angularchat.service;

import com.reka.lakatos.angularchat.document.ChatUser;
import com.reka.lakatos.angularchat.document.Status;
import com.reka.lakatos.angularchat.exception.UserNotFoundException;
import com.reka.lakatos.angularchat.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ChatUserRepository userRepository;

    public void registerNewUser(ChatUser user) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public ChatUser findUserByUserName(String name) {
        Optional<ChatUser> user = userRepository.findById(name);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("User not found.");
    }

    public void updateUserStatus(ChatUser user) {
        Optional<ChatUser> chatUser = userRepository.findById(user.getUserName());
        if (chatUser.isPresent()) {
            ChatUser presentUser = chatUser.get();
            presentUser.setStatus(user.getStatus());
            userRepository.save(presentUser);
            return;
        }
        throw new UserNotFoundException("User not found.");
    }

    public List<String> getActiveUsers() {
        List<ChatUser> allByStatusLike = userRepository.findAllByStatusLike(Status.ONLINE);
        return allByStatusLike.stream().map(ChatUser::getUserName).collect(Collectors.toList());
    }
}
