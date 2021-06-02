package com.programming.techie.springredditclone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StatementsDto {
//    private String Id;
//    private String AccountId;
//    private String datefield;
//    private String amount;

    private Long Id;
    private String AccountId;
    private LocalDate datefield;
    private BigDecimal amount;

}
