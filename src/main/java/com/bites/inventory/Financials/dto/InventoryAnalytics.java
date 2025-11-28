package com.bites.inventory.Financials.dto;

import java.math.BigDecimal;
import java.util.List;

public class InventoryAnalytics {
    private String itemName;
    private List<InventoryPurchaseSummary> purchases;
    private BigDecimal totalQuantityPurchased;
    private BigDecimal totalCost;
    private BigDecimal quantityUsed;
    private BigDecimal costUsed;

    public InventoryAnalytics() {}

    public InventoryAnalytics(String itemName, List<InventoryPurchaseSummary> purchases,
                              BigDecimal totalQuantityPurchased, BigDecimal totalCost,
                              BigDecimal quantityUsed, BigDecimal costUsed) {
        this.itemName = itemName;
        this.purchases = purchases;
        this.totalQuantityPurchased = totalQuantityPurchased;
        this.totalCost = totalCost;
        this.quantityUsed = quantityUsed;
        this.costUsed = costUsed;
    }

    // Getters
    public String getItemName() { return itemName; }
    public List<InventoryPurchaseSummary> getPurchases() { return purchases; }
    public BigDecimal getTotalQuantityPurchased() { return totalQuantityPurchased; }
    public BigDecimal getTotalCost() { return totalCost; }
    public BigDecimal getQuantityUsed() { return quantityUsed; }
    public BigDecimal getCostUsed() { return costUsed; }

    // Setters...
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setPurchases(List<InventoryPurchaseSummary> purchases) { this.purchases = purchases; }
    public void setTotalQuantityPurchased(BigDecimal totalQuantityPurchased) { this.totalQuantityPurchased = totalQuantityPurchased; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
    public void setQuantityUsed(BigDecimal quantityUsed) { this.quantityUsed = quantityUsed; }
    public void setCostUsed(BigDecimal costUsed) { this.costUsed = costUsed; }
}
