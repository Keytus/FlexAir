package com.model;

public class FortunePay {
    private String promoValue;
    private String paymentUrl;

    public FortunePay(String promoValue, String paymentUrl) {
        this.promoValue = promoValue;
        this.paymentUrl = paymentUrl;
    }

    public FortunePay() {
    }

    public String getPromoValue() {
        return promoValue;
    }

    public void setPromoValue(String promoValue) {
        this.promoValue = promoValue;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}
