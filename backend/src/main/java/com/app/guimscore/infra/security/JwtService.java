package com.app.guimscore.infra.security;

import com.app.guimscore.model.UserModel;
import com.app.guimscore.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;
    @Value("{secret.key}")
    private String secretKey;

    public String generateToken(UserModel userModel) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withExpiresAt(this.generateExpires())
                    .withIssuer("guymdx-fg")
                    .withSubject(userModel.getName())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Não foi possivel gerar jwt");
        }
    }

    public String validationToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("guymdx-fg")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Não foi possivel validar o token");
        }
    }

    public UUID getUserIdByToken(Authentication authentication) {

        try {
            if (authentication == null || !authentication.isAuthenticated()) {
                throw new RuntimeException("Usuário não autenticado");
            }

            UserModel userModel = (UserModel) this.userRepository.findByName(authentication.getName());
            return userModel.getUuid();

        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException.getMessage());
        }

    }

    private Instant generateExpires() {
        return LocalDateTime.now().plusYears(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
