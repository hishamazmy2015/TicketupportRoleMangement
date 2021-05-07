package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.TicketRequest;
import com.programming.techie.springredditclone.model.Ticket;
import com.programming.techie.springredditclone.security.JwtProvider;
import com.programming.techie.springredditclone.service.AuthService;
import com.programming.techie.springredditclone.service.TicketService;
import com.programming.techie.springredditclone.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final UserService userService;
    private final TicketService ticketService;
    private final UserDetailsService userDetailsService;
    private final AuthService authService;


    /**
     * Add  Ticket.
     *
     * @param ticket
     */
    @PostMapping("/add")
    public ResponseEntity<String> addTicket(/*@RequestHeader(name = "Authorization") String token,*/ @RequestBody TicketRequest ticket) {
        if (ticket.getMessage() == null || ticket.getMessage().isEmpty())
            return status(HttpStatus.EXPECTATION_FAILED).body("Please Fill the 'message' with value");
        else
            ticketService.saveTicket(ticket.getMessage());
        return status(HttpStatus.OK).body("Ticket has saved Successfully ");
    }

    /**
     * List all Ticket support.
     *
     * @param token
     */
    @GetMapping("/list")
//    @Secured(value = "admin")
    public ResponseEntity<List<Ticket>> getAllTicket(@RequestHeader(name = "Authorization") String token) {
        if (authService.getAuthorization(token))
            return status(HttpStatus.OK).body(ticketService.getAllTickets());
        else
            return status(HttpStatus.FORBIDDEN).body(null);
    }


}
