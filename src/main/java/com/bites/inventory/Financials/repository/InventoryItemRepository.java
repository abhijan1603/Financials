package com.bites.inventory.Financials.repository;

import com.bites.inventory.Financials.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findByActiveTrue();

    List<InventoryItem> findByActiveTrueAndCurrentStockLessThanEqual(java.math.BigDecimal stock);

}