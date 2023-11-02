package com.AiDatabase;

import jakarta.persistence.*;

@Entity
@Table
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "billname")
    private String billName;
    @Column(name = "summary")
    private String summary;

    public Response() {}

    public Response(Long id, String billName, String summary) {
        this.id = id;
        this.billName = billName;
        this.summary = summary;
    }

    public Response(String billName, String summary) {
        this.billName = billName;
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
