package com.bites.inventory.Financials.repository;

import com.bites.inventory.Financials.entity.InventoryPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InventoryPurchaseRepository extends JpaRepository<InventoryPurchase, Long> {
    List<InventoryPurchase> findByItemNameAndPurchaseDateBetweenOrderByPurchaseDate(String itemName, LocalDate from, LocalDate to);

    @Query("SELECT ip FROM InventoryPurchase ip WHERE ip.purchaseDate BETWEEN :from AND :to ORDER BY ip.itemName, ip.purchaseDate")
    List<InventoryPurchase> findAllBetween(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
