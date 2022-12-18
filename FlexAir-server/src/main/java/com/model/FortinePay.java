package com.model;

public class FortinePay {
    private String promoValue;
    private String paymentUrl;

    public FortinePay(String promoValue, String paymentUrl) {
        this.promoValue = promoValue;
        this.paymentUrl = paymentUrl;
    }

    public FortinePay() {
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
