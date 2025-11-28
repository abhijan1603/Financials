package com.bites.inventory.Financials.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sale_invoices")
public class SaleInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime invoiceDateTime;

    private String customerName;
    private String customerMobile;
    private String orderType;
    private String paymentMode;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal gstRate;

    @Column(precision = 15, scale = 2)
    private BigDecimal discount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal taxableAmount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal gstAmount;

    @Column(precision = 5, scale = 2)
    private BigDecimal roundOffAmount;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "saleInvoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleInvoiceItem> items = new ArrayList<>();

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getInvoiceDateTime() { return invoiceDateTime; }
    public void setInvoiceDateTime(LocalDateTime invoiceDateTime) { this.invoiceDateTime = invoiceDateTime; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerMobile() { return customerMobile; }
    public void setCustomerMobile(String customerMobile) { this.customerMobile = customerMobile; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public BigDecimal getGstRate() { return gstRate; }
    public void setGstRate(BigDecimal gstRate) { this.gstRate = gstRate; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getTaxableAmount() { return taxableAmount; }
    public void setTaxableAmount(BigDecimal taxableAmount) { this.taxableAmount = taxableAmount; }

    public BigDecimal getGstAmount() { return gstAmount; }
    public void setGstAmount(BigDecimal gstAmount) { this.gstAmount = gstAmount; }

    public BigDecimal getRoundOffAmount() { return roundOffAmount; }
    public void setRoundOffAmount(BigDecimal roundOffAmount) { this.roundOffAmount = roundOffAmount; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public List<SaleInvoiceItem> getItems() { return items; }
    public void setItems(List<SaleInvoiceItem> items) { this.items = items; }

    public void addItem(SaleInvoiceItem item) {
        items.add(item);
        item.setSaleInvoice(this);
    }
}