package com.bites.inventory.Financials.repository;

import com.bites.inventory.Financials.entity.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, Long> {
    List<SaleInvoice> findByInvoiceDateTimeBetween(LocalDateTime from, LocalDateTime to);

}