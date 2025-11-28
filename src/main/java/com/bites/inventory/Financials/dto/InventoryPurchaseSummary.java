package com.bites.inventory.Financials.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InventoryPurchaseSummary {
    private LocalDate date;
    private BigDecimal quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalCost;

    public InventoryPurchaseSummary() {}

    public InventoryPurchaseSummary(LocalDate date, BigDecimal quantity, BigDecimal pricePerUnit, BigDecimal totalCost) {
        this.date = date;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalCost = totalCost;
    }

    // Getters and setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public BigDecimal getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(BigDecimal pricePerUnit) { this.pricePerUnit = pricePerUnit; }
    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
}
