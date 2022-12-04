package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name="newsid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer newsID;
    @ManyToOne
    @JoinColumn(name = "infoblockid")
    private InfoBlock infoBlock;
    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    public Integer getNewsID() {
        return newsID;
    }

    public InfoBlock getInfoBlock() {
        return infoBlock;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public void setInfoBlock(InfoBlock infoBlock) {
        this.infoBlock = infoBlock;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "News[" +
                "newsID=" + newsID +
                ", infoBlock=" + infoBlock +
                ", customer=" + customer +
                ']';
    }
}
