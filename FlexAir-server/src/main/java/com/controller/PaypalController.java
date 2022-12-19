package com.controller;


import com.model.Message;
import com.model.Order;
import com.model.FortunePay;
import com.model.entity.Promocode;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.PaypalService;
import com.service.PromocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/pay")
public class PaypalController {
    @Autowired
    private PaypalService paypalService;
    @Autowired
    private PromocodeService promocodeService;
    public static final String SUCCESS = "/success";
    public static final String CANCEL = "/cancel";
    public static final String TICKET_SUCCESS = "/ticket/success";
    public static final String TICKET_CANCEL = "/ticket/cancel";
    public static final String FORTUNE_SUCCESS = "/fortune/success";
    public static final String FORTUNE_CANCEL = "/fortune/cancel";
    @PostMapping("/ticket")
    public ResponseEntity<?> paymentTicket(@RequestBody Order order) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();

        if (order.getPromoValue() != null){
            Promocode promocode = promocodeService.getPromocodeByValue(order.getPromoValue());
            order.setPrice(order.getPrice() - (order.getPrice() * promocode.getDiscount() / 100));
            order.setDescription(order.getDescription() + ";" + promocode.getPromocodeID());
        }

        try {
            Payment payment = paypalService.createTicketPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), uri + CANCEL,
                    uri + SUCCESS);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(new Message(link.getHref(), null), HttpStatus.OK);
                }
            }
        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("cancel", null), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping(value = TICKET_CANCEL)
    public ResponseEntity<Message> cancelTicketPay() {
        return new ResponseEntity<>(new Message("cancel", null), HttpStatus.OK);
    }

    @GetMapping(value = TICKET_SUCCESS)
    public ResponseEntity<Message> successTicketPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                paypalService.createTicket(payment.getTransactions().get(0).getDescription(),
                        payment.getTransactions().get(0).getAmount().getTotal());
                return new ResponseEntity<>(new Message("success", null), HttpStatus.OK);
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new Message("cancel", null), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @PostMapping("/fortune")
    public ResponseEntity<?> paymentFortune(@RequestBody Order order){
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        String newPromocodeValue = promocodeService.generatePromocodeValue();
        try {
            Payment payment = paypalService.createFortunePayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription() + ";" + newPromocodeValue, uri + CANCEL,
                    uri + SUCCESS);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(new FortunePay(newPromocodeValue, link.getHref()), HttpStatus.OK);
                }
            }
        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return new ResponseEntity<>(new Message("cancel", null), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping(value = FORTUNE_CANCEL)
    public ResponseEntity<Message> cancelFortunePay() {
        return new ResponseEntity<>(new Message("cancel", null), HttpStatus.OK);
    }

    @GetMapping(value = FORTUNE_SUCCESS)
    public ResponseEntity<Message> successFortunePay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                paypalService.createFortunePromocode(payment.getTransactions().get(0).getDescription());
                return new ResponseEntity<>(new Message("success", null), HttpStatus.OK);
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new Message("cancel", null), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
