package com.reka.lakatos.angularchat.security;

import com.reka.lakatos.angularchat.document.ChatUser;
import com.reka.lakatos.angularchat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ChatUser chatUser = userService.findUserByUserEmail(email);
        if (chatUser == null) {
            throw (new UsernameNotFoundException("Email: " + email + " not found"));
        }
        return new User(chatUser.getUserName(), chatUser.getPassword(),
                chatUser.getRolesInString().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }
}
