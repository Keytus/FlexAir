package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name="newsid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int newsID;
    @ManyToOne
    @JoinColumn(name = "infoblockid")
    private InfoBlock infoBlock;
    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    public int getNewsID() {
        return newsID;
    }

    public InfoBlock getInfoBlock() {
        return infoBlock;
    }

    public Customer getCustomer() {
        return customer;
    }
}
