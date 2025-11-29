package com.bites.inventory.Financials.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseTest {

    @Test
    public void testAllGettersAndSetters() {
        // 1. Setup sample data
        Long testId = 101L;
        String testCategory = "Travel";
        String testDescription = "Flight ticket";
        BigDecimal testAmount = new BigDecimal("199.99");
        LocalDate testDate = LocalDate.of(2025, 12, 25);

        // 2. Create the entity instance
        Expense expense = new Expense();

        // 3. Use the setter methods
        expense.setId(testId);
        expense.setCategory(testCategory);
        expense.setDescription(testDescription);
        expense.setAmount(testAmount);
        expense.setExpenseDate(testDate);

        // 4. Use the getter methods and assert the values are correct
        Assertions.assertEquals(testId, expense.getId(), "ID getter/setter failed");
        Assertions.assertEquals(testCategory, expense.getCategory(), "Category getter/setter failed");
        Assertions.assertEquals(testDescription, expense.getDescription(), "Description getter/setter failed");
        Assertions.assertEquals(testAmount, expense.getAmount(), "Amount getter/setter failed");
        Assertions.assertEquals(testDate, expense.getExpenseDate(), "Date getter/setter failed");
    }

    @Test
    public void testEntityCreation() {
        // Test entity instantiation with minimum required fields to ensure it holds data
        Expense expense = new Expense();
        expense.setCategory("Utilities");
        expense.setAmount(new BigDecimal("100.00"));
        expense.setExpenseDate(LocalDate.now());

        Assertions.assertNotNull(expense);
        Assertions.assertEquals("Utilities", expense.getCategory());
        Assertions.assertNull(expense.getId(), "ID should be null before persistence");
    }
}
