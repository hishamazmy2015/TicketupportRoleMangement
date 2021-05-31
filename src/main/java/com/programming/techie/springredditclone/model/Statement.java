package com.programming.techie.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Statement {
    @org.springframework.data.annotation.Id
    private Long Id;
    private Long AccountId;
    private String datefield;
    private String amount;
}
