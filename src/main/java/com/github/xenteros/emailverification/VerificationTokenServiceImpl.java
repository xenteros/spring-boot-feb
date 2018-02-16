package com.github.xenteros.emailverification;

import com.github.xenteros.security.User;
import com.github.xenteros.security.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;
    private JavaMailSender javaMailSender;
    private UserRepository userRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository, JavaMailSender javaMailSender, UserRepository userRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    @Override
    public void verify(String token) {
        verificationTokenRepository.findOneByToken(token)
                .ifPresent(t -> {
                    if (t.getExpiryDate().isAfter(LocalDateTime.now())){
                        t.getUser().enable();
                        userRepository.save(t.getUser());
                    }
                });
    }

    @Override
    public VerificationToken findOne(Long id) {
        return verificationTokenRepository.findOne(id);
    }


    @Scheduled(cron = "0 /5 * * * * ?")
    public void clearExpiredTokens() {
        Set<VerificationToken> tokens = verificationTokenRepository.findAllByUserIsEnabledFalse();
        final LocalDateTime now = LocalDateTime.now();
        Set<VerificationToken> expiredTokens = tokens.stream()
                .filter(token -> token.getExpiryDate().isBefore(now))
                .collect(Collectors.toSet());

        verificationTokenRepository.delete(expiredTokens);
        System.out.println("Cleaned tokens");
    }


    @Override
    public void createToken(User user) throws MessagingException {
        VerificationToken token = new VerificationToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        verificationTokenRepository.save(token);

        sendEmail(user.getEmail(), token.getToken());
    }

    private void sendEmail(String email, String token) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(email);
        helper.setSubject("Potwierdź email");
        helper.setText("http://localhost:8080/api/verification-token/" + token, false);
        javaMailSender.send(mail);
    }
}
