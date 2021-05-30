package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {
}
