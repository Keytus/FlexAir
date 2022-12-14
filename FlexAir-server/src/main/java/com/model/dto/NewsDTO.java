package com.model.dto;

public class NewsDTO {
    private Integer newsID;
    private String login;
    private String header;
    private String main;

    public NewsDTO(Integer newsID, String login, String header, String main) {
        this.newsID = newsID;
        this.login = login;
        this.header = header;
        this.main = main;
    }

    public NewsDTO() {
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        return "NewsDTO{" +
                "newsID=" + newsID +
                ", login='" + login + '\'' +
                ", header='" + header + '\'' +
                ", main='" + main + '\'' +
                '}';
    }
}
