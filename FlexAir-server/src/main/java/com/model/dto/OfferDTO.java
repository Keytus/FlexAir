package com.model.dto;

public class OfferDTO {
    private Integer offerID;
    private String promocodeValue;
    private String header;
    private String main;

    public OfferDTO(Integer offerID, String promocodeValue, String header, String main) {
        this.offerID = offerID;
        this.promocodeValue = promocodeValue;
        this.header = header;
        this.main = main;
    }

    public Integer getOfferID() {
        return offerID;
    }

    public void setOfferID(Integer offerID) {
        this.offerID = offerID;
    }

    public String getPromocodeValue() {
        return promocodeValue;
    }

    public void setPromocodeValue(String promocodeValue) {
        this.promocodeValue = promocodeValue;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "offerID=" + offerID +
                ", promocodeValue='" + promocodeValue + '\'' +
                ", header='" + header + '\'' +
                ", main='" + main + '\'' +
                '}';
    }
}
