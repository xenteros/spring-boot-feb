package com.github.xenteros.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password) {
        userService.registerUser(email, password);
        return "redirect:/hello";
    }
}
