package com.controller;

import com.model.entity.Ticket;
import com.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @GetMapping("/tickets")
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }
}
