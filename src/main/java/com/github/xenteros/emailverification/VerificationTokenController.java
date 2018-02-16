package com.github.xenteros.emailverification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verification-token")
public class VerificationTokenController {

    private VerificationTokenService verificationTokenService;

    @Autowired
    public VerificationTokenController(VerificationTokenService verificationTokenService) {
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping("/{token}")
    public void verify(@PathVariable String token) {
        verificationTokenService.verify(token);
    }

    @GetMapping
    public VerificationToken findTokenById(@RequestParam Long id) {
        return verificationTokenService.findOne(id);
    }


}
