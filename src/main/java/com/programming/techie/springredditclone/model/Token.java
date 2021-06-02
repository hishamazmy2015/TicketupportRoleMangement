package com.programming.techie.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Token {

    @Id
    private Long id;
    private String username;
    private String tokenvalue;
    private Instant expiredate;
//    private Instant expiredates;


    public Token(String token, String username, Instant expiredate/*, Instant instant*/) {
        this.tokenvalue = token;
        this.username = username;
        this.expiredate = expiredate;
//        this.expiredates = instant;
    }
}
