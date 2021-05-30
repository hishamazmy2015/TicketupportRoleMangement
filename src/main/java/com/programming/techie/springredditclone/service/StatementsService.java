package com.programming.techie.springredditclone.service;


import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.model.Statement;
import com.programming.techie.springredditclone.repository.StatementsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StatementsService {

    //    private final AuthService authService;
    private StatementsRepository statementsRepository;

    /*
     *
     *
     *
     * */
    @Transactional(readOnly = true)
    public List<StatementsDto> getStatmentsByArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        List<Statement> allByFilterArgs = statementsRepository.getAllByFilterArgs(accountId, fromDate, toDate, fromAmount, toAmount);
        System.out.println("allByFilterArgs " + allByFilterArgs);
        return null;
    }
    /*
     *
     *
     *
     * */
}

