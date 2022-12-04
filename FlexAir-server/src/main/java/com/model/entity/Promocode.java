package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "promocode")
public class Promocode {
    @Id
    @Column(name="promocodeid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer promocodeID;
    @Column(name="promocodevalue")
    private String promocodeValue;
    private Integer discount;
    @ManyToOne
    @JoinColumn(name="trackid")
    private Track track;
    @Column(name="usecount")
    private Integer useCount;

    public Integer getPromocodeID() {
        return promocodeID;
    }

    public String getPromocodeValue() {
        return promocodeValue;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Track getTrack() {
        return track;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setPromocodeID(Integer promocodeID) {
        this.promocodeID = promocodeID;
    }

    public void setPromocodeValue(String promocodeValue) {
        this.promocodeValue = promocodeValue;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    @Override
    public String toString() {
        return "Promocode[" +
                "promocodeID=" + promocodeID +
                ", promocodeValue='" + promocodeValue + '\'' +
                ", discount=" + discount +
                ", track=" + track +
                ", useCount=" + useCount +
                ']';
    }
}
