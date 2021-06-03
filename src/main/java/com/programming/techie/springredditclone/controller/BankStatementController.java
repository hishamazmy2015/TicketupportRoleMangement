package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.service.AuthService;
import com.programming.techie.springredditclone.service.HandleUtilityService;
//import com.programming.techie.springredditclone.service.StatementsService;
import com.programming.techie.springredditclone.service.StatementService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bank")
public class BankStatementController {

    private static final Logger logger = LogManager.getLogger(BankStatementController.class);

    private final StatementService statementsService;
    private final AuthService authService;
    private final HandleUtilityService handleUtilityService;


//?accountId=1&fromDate=05.07.2018&toDate=15.11.2020&fromAmount=166.470541608144&toAmount=386.908121686113

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
            @RequestParam(value = "accountId", required = false) String accountId,
            @RequestParam(value = "fromDate", required = false) String fromDate,
            @RequestParam(value = "toDate", required = false) String toDate,
            @RequestParam(value = "fromAmount", required = false) String fromAmount,
            @RequestParam(value = "toAmount", required = false) String toAmount
    ) {
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
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                return new ResponseEntity<>("You are not Authorized to filter By Args ", HttpStatus.UNAUTHORIZED);


            System.out.println("Last Three Month " + statementsService.getStatments(accountId, fromDate, toDate, fromAmount, toAmount));
            return status(HttpStatus.OK).body(statementsService.getStatments(accountId, fromDate, toDate, fromAmount, toAmount));
            //Log
        }
    }

}

/*













 */

//            return new ResponseEntity<String>("test", HttpStatus.UNAUTHORIZED);


//            return status(HttpStatus.UNAUTHORIZED).body("Not Allowed ");
//        return null;

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



