package com.bites.inventory.Financials.controller;

import com.bites.inventory.Financials.dto.SaleInvoiceRequest;
import com.bites.inventory.Financials.entity.SaleInvoice;
import com.bites.inventory.Financials.repository.SaleInvoiceRepository;
import com.bites.inventory.Financials.service.SaleInvoiceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin
public class SaleInvoiceController {
    private final SaleInvoiceService saleInvoiceService;
    private final SaleInvoiceRepository saleInvoiceRepository;

    public SaleInvoiceController(SaleInvoiceService saleInvoiceService,
                                 SaleInvoiceRepository saleInvoiceRepository) {
        this.saleInvoiceService = saleInvoiceService;
        this.saleInvoiceRepository = saleInvoiceRepository;
    }

    @PostMapping
    public SaleInvoice createInvoice(@RequestBody SaleInvoiceRequest request) {
        return saleInvoiceService.createInvoice(request);
    }

    @GetMapping
    public List<SaleInvoice> listInvoices(@RequestParam(required = false) String from,
                                          @RequestParam(required = false) String to) {
        if (from != null && to != null) {
            LocalDateTime fromDt = LocalDateTime.parse(from);
            LocalDateTime toDt = LocalDateTime.parse(to);
            return saleInvoiceRepository.findByInvoiceDateTimeBetween(fromDt, toDt);
        }
        return saleInvoiceRepository.findAll();
    }

}