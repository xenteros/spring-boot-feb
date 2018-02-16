package com.github.xenteros.security;

import com.github.xenteros.common.LoginAlreadyInUseException;
import com.github.xenteros.emailverification.VerificationToken;
import com.github.xenteros.emailverification.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public void registerUser(String email, String password) {

        if (userRepository.existsByEmail(email)) {
            throw new LoginAlreadyInUseException(email);
        }


        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        VerificationToken token = new VerificationToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusMinutes(5));

        verificationTokenRepository.save(token);

//        user = userRepository.save(user);

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        ctx.setAuthentication(auth);


    }
}
