package com.programming.techie.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VerificationToken {

    @Id
    private Long id;
    private String token;
    private User user;
    private Instant expiryDate;
}
