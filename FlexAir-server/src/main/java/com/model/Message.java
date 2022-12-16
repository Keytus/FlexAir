package com.model;

public class Message {
    private String status;
    private Integer id;

    public Message(String status, Integer id) {
        this.status = status;
        this.id = id;
    }

    public Message() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
