package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "promocode")
public class Promocode {
    @Id
    @Column(name="promocodeid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int promocodeID;
    @Column(name="promocodevalue")
    private String promocodeValue;
    private int discount;
    @ManyToOne
    @JoinColumn(name="trackid")
    private Track track;
    @Column(name="usecount")
    private int useCount;

    public int getPromocodeID() {
        return promocodeID;
    }

    public String getPromocodeValue() {
        return promocodeValue;
    }

    public int getDiscount() {
        return discount;
    }

    public Track getTrack() {
        return track;
    }

    public int getUseCount() {
        return useCount;
    }
}
