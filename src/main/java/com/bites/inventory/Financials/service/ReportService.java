package com.bites.inventory.Financials.service;

import com.bites.inventory.Financials.dto.DailySalesReportDTO;
import com.bites.inventory.Financials.dto.MonthlyReport;
import com.bites.inventory.Financials.entity.Expense;
import com.bites.inventory.Financials.entity.InventoryItem;
import com.bites.inventory.Financials.entity.SaleInvoice;
import com.bites.inventory.Financials.repository.ExpenseRepository;
import com.bites.inventory.Financials.repository.InventoryItemRepository;
import com.bites.inventory.Financials.repository.SaleInvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final SaleInvoiceRepository saleInvoiceRepository;
    private final ExpenseRepository expenseRepository;
    private final InventoryItemRepository inventoryItemRepository;

    public ReportService(SaleInvoiceRepository saleInvoiceRepository,
                         ExpenseRepository expenseRepository,
                         InventoryItemRepository inventoryItemRepository) {
        this.saleInvoiceRepository = saleInvoiceRepository;
        this.expenseRepository = expenseRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public MonthlyReport getMonthlyReport(LocalDate monthStart, LocalDate monthEnd) {
        // Revenue from sales
        List<SaleInvoice> sales = saleInvoiceRepository.findByInvoiceDateTimeBetween(
                monthStart.atStartOfDay(),
                monthEnd.atTime(23, 59, 59));

        BigDecimal totalSales = sales.stream()
                .map(SaleInvoice::getTaxableAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGst = sales.stream()
                .map(SaleInvoice::getGstAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Expenses
        List<Expense> expenses = expenseRepository.findByExpenseDateBetween(monthStart, monthEnd);

        BigDecimal totalExpenses = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Inventory cost
        List<InventoryItem> items = inventoryItemRepository.findAll();
        BigDecimal inventoryValue = items.stream()
                .map(item -> item.getCurrentStock().multiply(item.getAvgPurchasePricePerBaseUnit()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal profit = totalSales.subtract(totalExpenses).subtract(inventoryValue);

        return new MonthlyReport(totalSales, totalGst, totalExpenses, inventoryValue, profit);
    }

    public List<DailySalesReportDTO> getDetailedDailyReport(LocalDate from, LocalDate to) {
        List<SaleInvoice> allInvoices = saleInvoiceRepository.findByInvoiceDateTimeBetween(
                from.atStartOfDay(), to.atTime(23, 59, 59));

        // Group by Date
        Map<LocalDate, List<SaleInvoice>> grouped = allInvoices.stream()
                .collect(Collectors.groupingBy(
                        inv -> inv.getInvoiceDateTime().toLocalDate(),
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        List<DailySalesReportDTO> report = new ArrayList<>();

        for (Map.Entry<LocalDate, List<SaleInvoice>> entry : grouped.entrySet()) {
            LocalDate date = entry.getKey();
            List<SaleInvoice> dayInvoices = entry.getValue();

            BigDecimal dayRevenue = dayInvoices.stream()
                    .map(SaleInvoice::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            List<DailySalesReportDTO.CustomerTransaction> transactions = dayInvoices.stream()
                    .map(inv -> {
                        // Create item summary
                        String summary = inv.getItems().stream()
                                .map(item -> item.getDishName() + " x" + item.getQuantity())
                                .collect(Collectors.joining(", "));

                        String name = (inv.getCustomerName() == null || inv.getCustomerName().isEmpty()) ? "Walk-in" : inv.getCustomerName();

                        return new DailySalesReportDTO.CustomerTransaction(
                                name,
                                inv.getOrderType(),
                                inv.getPaymentMode(),
                                inv.getTotalAmount(),
                                summary
                        );
                    })
                    .collect(Collectors.toList());

            report.add(new DailySalesReportDTO(date, dayRevenue, dayInvoices.size(), transactions));
        }

        return report;
    }
}