package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @Column(name="offerid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int offerID;
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

    public int getOfferID() {
        return offerID;
    }
}
