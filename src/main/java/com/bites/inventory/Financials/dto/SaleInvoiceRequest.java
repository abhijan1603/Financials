package com.bites.inventory.Financials.dto;

import java.math.BigDecimal;
import java.util.List;

public class SaleInvoiceRequest {

    private String customerName;
    private String customerMobile;
    private String orderType;
    private String paymentMode;
    private BigDecimal gstRate;
    private BigDecimal discount;
    private BigDecimal roundOffAmount;

    private List<SaleInvoiceItemRequest> items;

    // Getters and Setters
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

    public BigDecimal getRoundOffAmount() { return roundOffAmount; }
    public void setRoundOffAmount(BigDecimal roundOffAmount) { this.roundOffAmount = roundOffAmount; }

    public List<SaleInvoiceItemRequest> getItems() { return items; }
    public void setItems(List<SaleInvoiceItemRequest> items) { this.items = items; }
}