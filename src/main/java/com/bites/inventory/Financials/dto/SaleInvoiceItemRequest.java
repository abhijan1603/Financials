package com.bites.inventory.Financials.dto;

import java.math.BigDecimal;

public class SaleInvoiceItemRequest {

    private String dishName; // <--- New Field
    private BigDecimal quantity;
    private String unit;
    private BigDecimal rate;

    public String getDishName() { return dishName; }
    public void setDishName(String dishName) { this.dishName = dishName; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }
}