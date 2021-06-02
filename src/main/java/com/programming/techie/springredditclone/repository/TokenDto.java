package com.programming.techie.springredditclone.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String username;
    private String tokenvalue;
    private String expiredate;
}
