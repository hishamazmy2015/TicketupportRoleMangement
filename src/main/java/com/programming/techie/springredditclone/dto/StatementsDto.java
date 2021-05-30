package com.programming.techie.springredditclone.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementsDto {
    private Long statementId;
    private String accountId;
    private LocalDate date;
    private BigDecimal amount;
}
