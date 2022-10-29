package com.example.hbmsserver.dto;

public class BudgetSpentValueDto {

    private final Long categoryTypeId;
    private final double spentValue;

    public BudgetSpentValueDto(Long categoryTypeId, double spentValue) {
        this.categoryTypeId = categoryTypeId;
        this.spentValue = spentValue;
    }

    public Long getCategoryTypeId() {
        return categoryTypeId;
    }

    public double getSpentValue() {
        return spentValue;
    }
}
