package com.github.xenteros.emailverification;

import com.github.xenteros.security.User;

import javax.mail.MessagingException;

public interface VerificationTokenService {

    void verify(String token);

    VerificationToken findOne(Long id);

    void createToken(User user) throws MessagingException;
}
