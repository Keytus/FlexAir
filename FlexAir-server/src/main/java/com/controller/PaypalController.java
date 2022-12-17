package com.controller;


import com.model.Message;
import com.model.Order;
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
            System.out.println(payment.toJSON());
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
    //                    RestTemplate restTemplate = new RestTemplate();
//
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//                    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
//
//                    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//
//                    ResponseEntity<?> result =
//                            restTemplate.exchange(uri + CANCEL_URL, HttpMethod.GET, entity, Message.class);
    //return new RedirectView(link.getHref());

}
