package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.exceptions.EntityNotFoundException;
import com.programming.techie.springredditclone.service.AuthService;
import com.programming.techie.springredditclone.service.HandleUtilityService;
import com.programming.techie.springredditclone.service.StatementService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bank")
public class BankStatementController {

    private static final Logger logger = LogManager.getLogger(BankStatementController.class);

    private final StatementService statementsService;
    private final AuthService authService;
    private final HandleUtilityService handleUtilityService;


    /**
     * Fetch Statements By Args.
     *
     * @param accountId
     * @param fromDate
     * @param toDate
     * @param fromAmount
     * @param toAmount
     */
    @GetMapping("/statements")
    public HttpEntity<?> getStatementsByParams(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam(value = "accountId", required = false) @Valid String accountId,
            @RequestParam(value = "fromDate", required = false) @Valid String fromDate,
            @RequestParam(value = "toDate", required = false) @Valid String toDate,
            @RequestParam(value = "fromAmount", required = false) @Valid String fromAmount,
            @RequestParam(value = "toAmount", required = false) @Valid String toAmount
    ) throws EntityNotFoundException {
        if (authService.getAuthorization(token)) {
            /**
             *
             * Inside Admin
             *
             * */
            System.out.println("Last Three Month " + statementsService.getStatments(accountId, fromDate, toDate, fromAmount, toAmount));
            return status(HttpStatus.OK).body(statementsService.getStatments(accountId, fromDate, toDate, fromAmount, toAmount));
        } else {
            /**
             *
             * Inside User
             *
             * */

            if (!handleUtilityService.areAllNull(accountId, fromDate, toDate, fromAmount, toAmount))
                return new ResponseEntity<>("You are not Authorized to filter By Args ", HttpStatus.UNAUTHORIZED);


            System.out.println("Last Three Month " + statementsService.getStatments(accountId, fromDate, toDate, fromAmount, toAmount));
            return status(HttpStatus.OK).body(statementsService.getStatments(accountId, fromDate, toDate, fromAmount, toAmount));
        }
    }

}
