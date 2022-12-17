package com.service;

import com.model.entity.*;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PaypalServiceImpl implements PaypalService{
    @Autowired
    private APIContext apiContext;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatSuiteRepository seatSuiteRepository;

    @Autowired
    private PromocodeRepository promocodeRepository;

    @Override
    public Payment createPayment(Double total, String currency, String method, String intent, String description,
                                 String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(total.toString());

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        if (checkSeatSuite(description)){
            return payment.create(apiContext);
        }
        else throw new ResourceNotFoundException("No available seats");
    }
    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }


    @Override
    public Ticket createTicket(String description, String total){
        Float cost = Float.parseFloat(total);
        List<String> items = Arrays.asList(description.split("\\s*;\\s*"));
        String seatClass = items.get(0);
        Integer flightID = Integer.parseInt(items.get(1));
        Integer customerID = Integer.parseInt(items.get(2));

        Ticket ticket = new Ticket();
        ticket.setTicketCost(cost);
        ticket.setSeatClass(seatClass);
        Customer customer = customerRepository.findById(customerID)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + customerID));
        ticket.setCustomer(customer);
        Flight flight = flightRepository.findById(flightID)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not exist with id :" + flightID));
        ticket.setFlight(flight);

        SeatSuite seatSuite = flight.getSeatSuite();
        switch (seatClass){
            case "economy":
                seatSuite.setEconomyReserved(seatSuite.getEconomyReserved() + 1);
                break;
            case "firstClass":
                seatSuite.setFirstClassReserved(seatSuite.getFirstClassTotal() + 1);
                break;
            case "lux":
                seatSuite.setLuxReserved(seatSuite.getLuxReserved() + 1);
                break;
            default:
                throw new ResourceNotFoundException("Invalid seat class");
        }

        if (items.size() == 4){
            Integer promocodeID = Integer.parseInt(items.get(3));
            Promocode promocode = promocodeRepository.findById(promocodeID)
                    .orElseThrow(() -> new ResourceNotFoundException("Promocode not exist with id :" + promocodeID));
            promocode.setUseCount(promocode.getUseCount() - 1);
            promocodeRepository.save(promocode);
        }

        seatSuiteRepository.save(seatSuite);

        ticketRepository.save(ticket);

        return ticket;
    }
    @Override
    public Promocode getPromocodeByValue(String value){
        return promocodeRepository.findByPromocodeValue(value).get(0);
    }

    private boolean checkSeatSuite(String description){
        List<String> items = Arrays.asList(description.split("\\s*;\\s*"));
        String seatClass = items.get(0);
        Integer flightID = Integer.parseInt(items.get(1));
        Flight flight = flightRepository.findById(flightID)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not exist with id :" + flightID));
        SeatSuite seatSuite = flight.getSeatSuite();
        switch (seatClass){
            case "economy":
                if (seatSuite.getEconomyReserved() == seatSuite.getEconomyTotal()){
                    return false;
                }
                break;
            case "firstClass":
                seatSuite.setFirstClassReserved(seatSuite.getFirstClassTotal() + 1);
                if (seatSuite.getFirstClassReserved() == seatSuite.getFirstClassTotal()) {
                    return false;
                }
                break;
            case "lux":
                seatSuite.setLuxReserved(seatSuite.getLuxReserved() + 1);
                if (seatSuite.getLuxReserved() == seatSuite.getLuxTotal()) {
                    return false;
                }
                break;
            default:
                throw new ResourceNotFoundException("Invalid seat class");
        }
        return true;
    }
}
