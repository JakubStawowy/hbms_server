package com.example.hbmsserver.model;

import com.example.hbmsserver.dto.TransactionInfoDto;

import javax.persistence.*;

@Entity
@Table(name = "user_transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private double value;

    @Column(name = "date")
    private String date;

    public Transaction() {
    }

    public Transaction(Category category, String name, double value, String date) {
        this.category = category;
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public Transaction(TransactionInfoDto transactionInfoDto, Category category) {
        this.category = category;
        this.name = transactionInfoDto.getName();
        this.value = transactionInfoDto.getValue();
        this.date = transactionInfoDto.getDay();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}