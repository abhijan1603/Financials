package com.bites.inventory.Financials.service;

import com.bites.inventory.Financials.dto.InventoryAnalytics;
import com.bites.inventory.Financials.dto.InventoryPurchaseSummary;
import com.bites.inventory.Financials.entity.InventoryPurchase;
import com.bites.inventory.Financials.repository.InventoryPurchaseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryPurchaseService {
    private final InventoryPurchaseRepository repository;

    public InventoryPurchaseService(InventoryPurchaseRepository repository) {
        this.repository = repository;
    }

    public InventoryPurchase createPurchase(InventoryPurchase purchase) {
        purchase.setTotalCost(purchase.getQuantity().multiply(purchase.getPricePerUnit()));
        return repository.save(purchase);
    }

    public List<InventoryPurchase> getAllPurchases(LocalDate from, LocalDate to) {
        return repository.findAllBetween(from, to);
    }

    public InventoryAnalytics getItemAnalytics(String itemName, LocalDate from, LocalDate to) {
        List<InventoryPurchase> purchases = repository.findByItemNameAndPurchaseDateBetweenOrderByPurchaseDate(itemName, from, to);

        List<InventoryPurchaseSummary> summaries = purchases.stream()
                .map(p -> new InventoryPurchaseSummary(p.getPurchaseDate(), p.getQuantity(),
                        p.getPricePerUnit(), p.getTotalCost()))
                .collect(Collectors.toList());

        BigDecimal totalQty = purchases.stream()
                .map(InventoryPurchase::getQuantity)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCost = purchases.stream()
                .map(InventoryPurchase::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Simplified: assume 80% usage rate for demo (replace with actual sales data later)
        BigDecimal quantityUsed = totalQty.multiply(BigDecimal.valueOf(0.8)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal costUsed = totalCost.multiply(BigDecimal.valueOf(0.8)).setScale(2, RoundingMode.HALF_UP);

        return new InventoryAnalytics(itemName, summaries, totalQty, totalCost, quantityUsed, costUsed);
    }
}
