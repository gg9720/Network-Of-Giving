package com.example.demo.config;

import com.example.demo.models.pojo.User;
import com.example.demo.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public AuthProvider(UserService userService) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        Optional<User> optionalAccount = userService.getUserByUsername(username);
        if (!optionalAccount.isPresent()) {
            return null;
        }

        User user = optionalAccount.get();
        String credentials = String.valueOf(authentication.getCredentials());
        if (!passwordEncoder.matches(credentials, user.getPassword())) {
            return null;
        }

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        user,
                        authentication.getCredentials(),new ArrayList<>());
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
