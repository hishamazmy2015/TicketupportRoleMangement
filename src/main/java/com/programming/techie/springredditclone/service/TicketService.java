package com.programming.techie.springredditclone.service;


import com.programming.techie.springredditclone.model.Ticket;
import com.programming.techie.springredditclone.model.User;
import com.programming.techie.springredditclone.repository.TicketRepository;
import com.programming.techie.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Transactional
    public void saveTicket(String message) {
        User currentUser = authService.getCurrentUser();
        Ticket ticket = new Ticket(String.valueOf(currentUser.getUserId()), message);
        ticketRepository.save(ticket);
    }


}

