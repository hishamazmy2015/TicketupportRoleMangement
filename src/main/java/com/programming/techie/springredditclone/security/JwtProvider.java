package com.programming.techie.springredditclone.security;

import com.programming.techie.springredditclone.exceptions.CustomException;
import com.programming.techie.springredditclone.repository.VerificationTokenRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;

import static io.jsonwebtoken.Jwts.parserBuilder;
import static java.util.Date.from;

@Service
@Slf4j
public class JwtProvider {
    private final Logger log = LoggerFactory.getLogger(JwtProvider.class);
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;
    private static final String ExpiredJwtExceptionSTR = "ExpiredJwtException";

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new CustomException("Exception occurred while loading keystore", e);
        }

    }

    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new CustomException("Exception occured while retrieving public key from keystore", e);
        }
    }

    public boolean validateToken(String jwt) {
        try {
            parserBuilder().setSigningKey(getPublickey()).build().parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.error("INVALID_CREDENTIALS " + ex);
            return false;
        } catch (ExpiredJwtException ex) {
            log.error(ExpiredJwtExceptionSTR + ex);
            deleteToken(jwt);
            return false;
        }
    }


    public boolean expirationToken(String jwt) {
        try {
            System.out.println("Instant.now()  " + Instant.now());
            Instant instant = parserBuilder()
                    .setSigningKey(getPublickey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getExpiration().toInstant();
            System.out.println("Expire date " + instant.compareTo(Instant.now()));
            if (instant.isAfter(Instant.now()))
                return true;
            else
                return false;
        } catch (ExpiredJwtException e) {
            System.out.println(" Token expired ");
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.error(ExpiredJwtExceptionSTR + ex);
            return true;
        } catch (Exception e) {
            System.out.println("e ============ " + e);
            log.error("Exception " + e);
        }
        return false;
    }

    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new CustomException("Exception occured while " +
                    "retrieving public key from keystore", e);
        }
    }

    public String getUsernameFromJwt(String token) {
        try {
            Claims claims = parserBuilder()
                    .setSigningKey(getPublickey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println(" Token expired ");
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.error(ExpiredJwtExceptionSTR + ex);
        } catch (Exception e) {
            System.out.println("e ============ " + e);
            log.error("Exception has found " + e);
        }
        return "";
    }

    public void deleteToken(String token) {
        verificationTokenRepository.deleteByToken(token);
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
