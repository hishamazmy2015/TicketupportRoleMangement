package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.StatementsDto;
import com.programming.techie.springredditclone.model.Ticket;
import com.programming.techie.springredditclone.service.AuthService;
import com.programming.techie.springredditclone.service.StatementsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public HttpEntity<? extends Object> getStatementsByParams(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam(value = "accountId") String accountId,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "fromAmount", required = false) String fromAmount,
            @RequestParam(value = "toAmount", required = false) String toAmount
    ) {
        if (authService.getAuthorization(token)) {
            /**
             * Inside Admin
             * */

            if (statementsService.areAllNull(accountId, fromDate, toDate, fromAmount, toAmount))
                return status(HttpStatus.OK).body((List<StatementsDto>) statementsService.getStatmentsByArgs(accountId, fromDate, toDate, fromAmount, toAmount));
            else
                return status(HttpStatus.OK).body((List<StatementsDto>) statementsService.getStatmentsByArgs(accountId, fromDate, toDate, fromAmount, toAmount));


        } else {
            /**
             * Inside User
             * */
            statementsService.getStatmentsByThreeMoths(accountId, fromDate, toDate, fromAmount, toAmount);

            return new ResponseEntity<String>("test", HttpStatus.UNAUTHORIZED);
        }
        //            return status(HttpStatus.UNAUTHORIZED).body("Not Allowed ");
//        return null;
    }

}

/*













 */
//        else
//        return status(HttpStatus.FORBIDDEN).body(null);
//        statementsService.getAllTickets();
//        if (ticket.getMessage() == null || ticket.getMessage().isEmpty())
//            return status(HttpStatus.EXPECTATION_FAILED).body("Please Fill the 'message' with value");
//        else
//            ticketService.saveTicket(ticket.getMessage());
//        return status(HttpStatus.OK).body("statement has saved Successfully ");


//    /**
//     * List all Ticket support.
//     *
//     * @param token
//     */
//    @GetMapping("/list")
////    @Secured(value = "admin")
//    public ResponseEntity<List<Ticket>> getAllTicket(@RequestHeader(name = "Authorization") String token) {
//        if (authService.getAuthorization(token))
//            return status(HttpStatus.OK).body(ticketService.getAllTickets());
//        else
//            return status(HttpStatus.FORBIDDEN).body(null);
//    }



