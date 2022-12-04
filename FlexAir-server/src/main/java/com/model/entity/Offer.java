package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @Column(name="offerid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer offerID;
    @ManyToOne
    @JoinColumn(name = "infoblockid")
    private InfoBlock infoBlock;
    @ManyToOne
    @JoinColumn(name = "promocodeid")
    private Promocode promocode;

    public InfoBlock getInfoBlock() {
        return infoBlock;
    }

    public Promocode getPromocode() {
        return promocode;
    }

    public Integer getOfferID() {
        return offerID;
    }

    public void setOfferID(Integer offerID) {
        this.offerID = offerID;
    }

    public void setInfoBlock(InfoBlock infoBlock) {
        this.infoBlock = infoBlock;
    }

    public void setPromocode(Promocode promocode) {
        this.promocode = promocode;
    }

    @Override
    public String toString() {
        return "Offer[" +
                "offerID=" + offerID +
                ", infoBlock=" + infoBlock +
                ", promocode=" + promocode +
                ']';
    }
}
