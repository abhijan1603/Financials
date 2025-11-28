package com.bites.inventory.Financials.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_invoice_items")
public class SaleInvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private SaleInvoice saleInvoice;

    // --- NEW FIELD ---
    private String dishName;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, length = 20)
    private String unit;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal rate;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal lineTotal;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public SaleInvoice getSaleInvoice() { return saleInvoice; }
    public void setSaleInvoice(SaleInvoice saleInvoice) { this.saleInvoice = saleInvoice; }

    public String getDishName() { return dishName; } // <--- The missing method!
    public void setDishName(String dishName) { this.dishName = dishName; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getRate() { return rate; }
    public void setRate(BigDecimal rate) { this.rate = rate; }

    public BigDecimal getLineTotal() { return lineTotal; }
    public void setLineTotal(BigDecimal lineTotal) { this.lineTotal = lineTotal; }
}