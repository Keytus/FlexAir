package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name="ticketid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketID;
    @Column(name="seatclass")
    private String seatClass;
    @Column(name="ticketcost")
    private  float ticketCost;
    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "flightid")
    private Flight flight;

    public int getTicketID() {
        return ticketID;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public float getTicketCost() {
        return ticketCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Flight getFlight() {
        return flight;
    }
}
