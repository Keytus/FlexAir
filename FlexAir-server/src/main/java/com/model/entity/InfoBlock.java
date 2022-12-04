package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "infoblock")
public class InfoBlock {
    @Id
    @Column(name="infoblockid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer infoBlockID;
    private String header;
    private String main;

    public Integer getInfoBlockID() {
        return infoBlockID;
    }

    public String getHeader() {
        return header;
    }

    public String getMain() {
        return main;
    }

    public void setInfoBlockID(Integer infoBlockID) {
        this.infoBlockID = infoBlockID;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setMain(String main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "InfoBlock[" +
                "infoBlockID=" + infoBlockID +
                ", header='" + header + '\'' +
                ", main='" + main + '\'' +
                ']';
    }
}
