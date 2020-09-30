package com.reka.lakatos.angularchat.controller;

import com.reka.lakatos.angularchat.model.UserCredentials;
import com.reka.lakatos.angularchat.security.JwtTokenServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;

    @PostMapping("/login")
    public ResponseEntity logIn(@RequestBody UserCredentials data, HttpServletResponse response) {
        try {
            String userEmail = data.getUserEmail();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());


            String token = jwtTokenServices.createToken(userEmail, roles);
            Cookie cookieToken = new Cookie("token", token);
            cookieToken.setMaxAge(60 * 60 * 24);
            cookieToken.setHttpOnly(true);
            cookieToken.setPath("/");
            response.addCookie(cookieToken);
            return ResponseEntity.ok(roles);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid userEmail/password supplied");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest req, HttpServletResponse response) {
        try {
            Cookie cookieToken = new Cookie("token", null);
            cookieToken.setMaxAge(0);
            cookieToken.setHttpOnly(true);
            cookieToken.setPath("/");
            response.addCookie(cookieToken);

            return ResponseEntity.ok("");

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Logout failed");
        }
    }

}
