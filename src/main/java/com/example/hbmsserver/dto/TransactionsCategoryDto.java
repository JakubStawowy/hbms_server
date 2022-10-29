package com.example.hbmsserver.dto;

import java.util.List;

public class TransactionsCategoryDto {

    private final String typeName;
    private final String date;
    private final String color;
    private final String icon;
    private final List<TransactionInfoDto> transactions;

    public TransactionsCategoryDto(String typeName, String date, String color, String icon, List<TransactionInfoDto> transactions) {
        this.typeName = typeName;
        this.date = date;
        this.color = color;
        this.icon = icon;
        this.transactions = transactions;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDate() {
        return date;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public List<TransactionInfoDto> getTransactions() {
        return transactions;
    }
}
