package com.service;

import com.model.entity.Ticket;
import com.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}
