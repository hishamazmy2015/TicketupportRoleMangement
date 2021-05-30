package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.service.AuthService;
import com.programming.techie.springredditclone.service.StatementsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bank")
public class BankStatementController {


    private final StatementsService statementsService;
    private final AuthService authService;


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
    public ResponseEntity<String> getStatementsByParams(
            @RequestParam(value = "accountId") String accountId,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "fromAmount", required = false) String fromAmount,
            @RequestParam(value = "toAmount", required = false) String toAmount
    ) {
        List<StatementsDto> statments = statementsService.getStatmentsByArgs(accountId, fromDate, toDate, fromAmount, toAmount);

//        statementsService.getAllTickets();
//        if (ticket.getMessage() == null || ticket.getMessage().isEmpty())
//            return status(HttpStatus.EXPECTATION_FAILED).body("Please Fill the 'message' with value");
//        else
//            ticketService.saveTicket(ticket.getMessage());
        return status(HttpStatus.OK).body("statement has saved Successfully ");
    }


}
