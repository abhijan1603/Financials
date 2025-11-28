package com.bites.inventory.Financials.controller;

import com.bites.inventory.Financials.dto.InventoryAnalytics;
import com.bites.inventory.Financials.entity.InventoryPurchase;
import com.bites.inventory.Financials.service.InventoryPurchaseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
public class InventoryPurchaseController {
    private final InventoryPurchaseService service;

    public InventoryPurchaseController(InventoryPurchaseService service) {
        this.service = service;
    }

    @PostMapping("/purchases")
    public InventoryPurchase createPurchase(@RequestBody InventoryPurchase purchase) {
        return service.createPurchase(purchase);
    }

    @GetMapping("/purchases")
    public List<InventoryPurchase> getPurchases(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return service.getAllPurchases(from, to);
    }

    @GetMapping("/analytics/{itemName}")
    public InventoryAnalytics getItemAnalytics(
            @PathVariable String itemName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return service.getItemAnalytics(itemName, from, to);
    }
}
