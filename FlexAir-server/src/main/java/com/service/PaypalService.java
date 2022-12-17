package com.service;

import com.model.entity.Promocode;
import com.model.entity.Ticket;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
    public Payment createPayment(Double total, String currency, String method, String intent, String description,
            String cancelUrl, String successUrl) throws PayPalRESTException;
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
    public Ticket createTicket(String description, String total);
    public Promocode getPromocodeByValue(String value);
}
