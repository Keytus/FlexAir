package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "infoblock")
public class InfoBlock {
    @Id
    @Column(name="infoblockid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int infoBlockID;
    private String header;
    private String main;

    public int getInfoBlockID() {
        return infoBlockID;
    }

    public String getHeader() {
        return header;
    }

    public String getMain() {
        return main;
    }
}
