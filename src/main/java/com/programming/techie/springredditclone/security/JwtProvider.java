package com.programming.techie.springredditclone.security;

import com.programming.techie.springredditclone.exceptions.CustomException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;
//import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.Jwts.parserBuilder;
import static java.util.Date.from;

@Service
@Slf4j
public class JwtProvider {
    private final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

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
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
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
            log.error("ExpiredJwtException " + ex);
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
            log.error("ExpiredJwtException " + ex);
            return true;
        } catch (Exception e) {
            System.out.println("e ============ " + e);
            log.error("ExpiredJwtException " + e);
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
            log.error("ExpiredJwtException " + ex);
        } catch (Exception e) {
            System.out.println("e ============ " + e);
            log.error("ExpiredJwtException " + e);
        }
        return "";
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
