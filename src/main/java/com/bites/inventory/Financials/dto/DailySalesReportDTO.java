package com.bites.inventory.Financials.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DailySalesReportDTO {
    private LocalDate date;
    private BigDecimal totalRevenue;
    private int totalOrders;
    private List<CustomerTransaction> transactions;

    // Inner class for individual transactions
    public static class CustomerTransaction {
        private String customerName;
        private String orderType;     // Dine-in, Takeaway
        private String paymentMode;   // Cash, UPI
        private BigDecimal billAmount;
        private String itemsSummary;  // "Biryani x2, Coke x1"

        public CustomerTransaction(String customerName, String orderType, String paymentMode, BigDecimal billAmount, String itemsSummary) {
            this.customerName = customerName;
            this.orderType = orderType;
            this.paymentMode = paymentMode;
            this.billAmount = billAmount;
            this.itemsSummary = itemsSummary;
        }

        // Getters
        public String getCustomerName() { return customerName; }
        public String getOrderType() { return orderType; }
        public String getPaymentMode() { return paymentMode; }
        public BigDecimal getBillAmount() { return billAmount; }
        public String getItemsSummary() { return itemsSummary; }
    }

    public DailySalesReportDTO(LocalDate date, BigDecimal totalRevenue, int totalOrders, List<CustomerTransaction> transactions) {
        this.date = date;
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
        this.transactions = transactions;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public int getTotalOrders() { return totalOrders; }
    public List<CustomerTransaction> getTransactions() { return transactions; }
}