package com.bites.inventory.Financials.repository;

import com.bites.inventory.Financials.entity.SaleInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleInvoiceItemRepository extends JpaRepository<SaleInvoiceItem, Long> {
}