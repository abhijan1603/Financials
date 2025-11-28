package com.bites.inventory.Financials.dto;

import java.math.BigDecimal;

public class MonthlyReport {
    private BigDecimal totalSales;
    private BigDecimal totalGst;
    private BigDecimal totalExpenses;
    private BigDecimal inventoryCost;
    private BigDecimal profit;

    // Default constructor for JSON deserialization
    public MonthlyReport() {}

    public MonthlyReport(BigDecimal totalSales, BigDecimal totalGst,
                         BigDecimal totalExpenses, BigDecimal inventoryCost, BigDecimal profit) {
        this.totalSales = totalSales;
        this.totalGst = totalGst;
        this.totalExpenses = totalExpenses;
        this.inventoryCost = inventoryCost;
        this.profit = profit;
    }

    // Getters and Setters
    public BigDecimal getTotalSales() {
        return totalSales != null ? totalSales : BigDecimal.ZERO;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public BigDecimal getTotalGst() {
        return totalGst != null ? totalGst : BigDecimal.ZERO;
    }

    public void setTotalGst(BigDecimal totalGst) {
        this.totalGst = totalGst;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses != null ? totalExpenses : BigDecimal.ZERO;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getInventoryCost() {
        return inventoryCost != null ? inventoryCost : BigDecimal.ZERO;
    }

    public void setInventoryCost(BigDecimal inventoryCost) {
        this.inventoryCost = inventoryCost;
    }

    public BigDecimal getProfit() {
        return profit != null ? profit : BigDecimal.ZERO;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
