package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name="ticketid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer ticketID;
    @Column(name="seatclass")
    private String seatClass;
    @Column(name="ticketcost")
    private Float ticketCost;
    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "flightid")
    private Flight flight;

    public Integer getTicketID() {
        return ticketID;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public Float getTicketCost() {
        return ticketCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public void setTicketCost(Float ticketCost) {
        this.ticketCost = ticketCost;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Ticket[" +
                "ticketID=" + ticketID +
                ", seatClass='" + seatClass + '\'' +
                ", ticketCost=" + ticketCost +
                ", customer=" + customer +
                ", flight=" + flight +
                ']';
    }
}
