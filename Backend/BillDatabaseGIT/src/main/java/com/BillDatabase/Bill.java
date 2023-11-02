package com.BillDatabase;

import jakarta.persistence.*;

@Entity
@Table
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String billName;
    private String billSummary;

    public Bill() {}
    public Bill(Long id, String billName, String billSummary) {
        this.id = id;
        this.billName = billName;
        this.billSummary = billSummary;
    }

    public Bill(String billName, String billSummary) {
        this.billName = billName;
        this.billSummary = billSummary;
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

    public String getBillSummary() {
        return billSummary;
    }

    public void setBillSummary(String billSummary) {
        this.billSummary = billSummary;
    }
}
