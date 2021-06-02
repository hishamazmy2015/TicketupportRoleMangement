package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.StatementsDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StatementService {
    List<StatementsDto> getStatments(String accountId, String fromDate, String toDate, String fromAmount, String toAmount);

}
