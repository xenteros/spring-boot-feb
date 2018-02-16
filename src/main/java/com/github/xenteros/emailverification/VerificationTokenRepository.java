package com.github.xenteros.emailverification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findOneByToken(String token);

    Set<VerificationToken> findAllByUserIsEnabledFalse();

}
