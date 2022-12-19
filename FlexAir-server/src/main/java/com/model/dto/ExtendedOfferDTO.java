package com.model.dto;

public class ExtendedOfferDTO {
    private Integer offerID;
    private String promocodeValue;
    private Integer discount;
    private Integer useCount;
    private Integer trackID;
    private String header;
    private String main;

    public ExtendedOfferDTO(Integer offerID, String promocodeValue, Integer discount, Integer useCount, Integer trackID, String header, String main) {
        this.offerID = offerID;
        this.promocodeValue = promocodeValue;
        this.discount = discount;
        this.useCount = useCount;
        this.trackID = trackID;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getTrackID() {
        return trackID;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
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
        return "ExtendedOfferDTO{" +
                "offerID=" + offerID +
                ", promocodeValue='" + promocodeValue + '\'' +
                ", discount=" + discount +
                ", useCount=" + useCount +
                ", trackID=" + trackID +
                ", header='" + header + '\'' +
                ", main='" + main + '\'' +
                '}';
    }
}
