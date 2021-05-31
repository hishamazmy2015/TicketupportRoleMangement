package com.programming.techie.springredditclone.service;


import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.mapper.StatementsMapper;
import com.programming.techie.springredditclone.model.Statement;
import com.programming.techie.springredditclone.repository.StatementsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class StatementsService {

    //    private final AuthService authService;
    private StatementsRepository statementsRepository;
    private StatementsMapper statementsMapper;


    /*
     *
     *
     *
     * */
    @Transactional(readOnly = true)
    public List<StatementsDto> getStatmentsByArgs(String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        return
                statementsRepository.getAllByFilterArgs(accountId, fromDate, toDate, fromAmount, toAmount).stream().
                        map(statementsMapper::mapDtoToStatementDto).
                        collect(toList());
    }


    public static boolean areAllNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }

    public static boolean areAllNotNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::nonNull);
    }

    /**
     * The search will return three months back.
     */
    @Transactional(readOnly = true)
    public List<StatementsDto> getStatmentsByThreeMoths(String accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        return
                statementsRepository.getAllByFilterArgs(accountId, fromDate, toDate, fromAmount, toAmount).stream().
                        map(statementsMapper::mapDtoToStatementDto).
                        collect(toList());
    }

    /*
     *
     *
     *
     * */
}

