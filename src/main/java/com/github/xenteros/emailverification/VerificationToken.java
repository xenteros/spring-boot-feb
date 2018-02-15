package com.github.xenteros.emailverification;

import com.github.xenteros.common.BaseEntity;
import com.github.xenteros.security.User;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class VerificationToken extends BaseEntity {

    private User user;
    private String token;
    private LocalDateTime expiryDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
