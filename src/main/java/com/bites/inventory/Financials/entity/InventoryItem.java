package com.bites.inventory.Financials.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory_items")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // KG, GM, LT, PIECE, PACKET, BOTTLE, etc.
    @Column(nullable = false, length = 20)
    private String baseUnit;

    // Stored in baseUnit (e.g. KG)
    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal currentStock = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal reorderLevel = BigDecimal.ZERO;

    // Average purchase price per base unit (e.g. per KG)
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal avgPurchasePricePerBaseUnit = BigDecimal.ZERO;

    @Column(nullable = false)
    private boolean active = true;

// Getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBaseUnit() { return baseUnit; }

    public void setBaseUnit(String baseUnit) { this.baseUnit = baseUnit; }

    public BigDecimal getCurrentStock() { return currentStock; }

    public void setCurrentStock(BigDecimal currentStock) { this.currentStock = currentStock; }

    public BigDecimal getReorderLevel() { return reorderLevel; }

    public void setReorderLevel(BigDecimal reorderLevel) { this.reorderLevel = reorderLevel; }

    public BigDecimal getAvgPurchasePricePerBaseUnit() { return avgPurchasePricePerBaseUnit; }

    public void setAvgPurchasePricePerBaseUnit(BigDecimal avgPurchasePricePerBaseUnit) {
        this.avgPurchasePricePerBaseUnit = avgPurchasePricePerBaseUnit;
    }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

}