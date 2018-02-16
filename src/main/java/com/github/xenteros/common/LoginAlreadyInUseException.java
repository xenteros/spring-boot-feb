package com.github.xenteros.common;

public class LoginAlreadyInUseException extends RuntimeException {

    private String login;

    public LoginAlreadyInUseException(String login) {
        super(String.format("Email %s is already used", login));
        this.login = login;
    }
}
