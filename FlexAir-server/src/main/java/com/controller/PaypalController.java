package com.controller;


import com.model.Message;
import com.model.Order;
import com.model.entity.Promocode;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.PaypalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/pay")
public class PaypalController {
    private PaypalService paypalService;

    public static final String SUCCESS_URL = "/success";
    public static final String CANCEL_URL = "/cancel";

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }
    @PostMapping("/")
    public ResponseEntity<?> payment(@RequestBody Order order) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();

        if (order.getPromoValue() != null){
            Promocode promocode = paypalService.getPromocodeByValue(order.getPromoValue());
            order.setPrice(order.getPrice() - (order.getPrice() * promocode.getDiscount() / 100));
            order.setDescription(order.getDescription() + ";" + promocode.getPromocodeID());
        }

        try {
            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), uri + CANCEL_URL.substring(1),
                    uri + SUCCESS_URL.substring(1));
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

    @GetMapping(value = CANCEL_URL)
    public ResponseEntity<Message> cancelPay() {
        return new ResponseEntity<>(new Message("cancel", null), HttpStatus.OK);
    }

    @GetMapping(value = SUCCESS_URL)
    public ResponseEntity<Message> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
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
}
