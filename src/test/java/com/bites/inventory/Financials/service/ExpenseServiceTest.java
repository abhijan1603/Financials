package com.bites.inventory.Financials.service;

import com.bites.inventory.Financials.entity.Expense;
import com.bites.inventory.Financials.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// This extension initializes Mockito annotations (@Mock, @InjectMocks)
@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    // Creates a mock instance of the dependency
    @Mock
    private ExpenseRepository expenseRepository;

    // Injects the mocks into the service class we are testing
    @InjectMocks
    private ExpenseService expenseService;

    private Expense testExpense;

    @BeforeEach
    public void setUp() {
        testExpense = new Expense();
        testExpense.setId(1L);
        testExpense.setDescription("Coffee");
        testExpense.setCategory("Food");
        testExpense.setAmount(new BigDecimal("5.50"));
        testExpense.setExpenseDate(LocalDate.now());
    }

    // --- Test Cases ---

    @Test
    public void createExpenseShouldSaveAndReturnExpense() {
        // Mock the behavior of the repository's save method
        when(expenseRepository.save(any(Expense.class))).thenReturn(testExpense);

        // Call the service method we are actually testing
        Expense createdExpense = expenseService.create(testExpense);

        // Assertions:
        assertEquals(testExpense.getDescription(), createdExpense.getDescription());
        assertEquals(1L, createdExpense.getId());

        // Verification: Ensure the save method was actually called exactly once with the correct object
        verify(expenseRepository).save(testExpense);
    }

    @Test
    public void findBetweenDatesShouldReturnFilteredList() {
        LocalDate from = LocalDate.of(2023, 10, 1);
        LocalDate to = LocalDate.of(2023, 10, 31);
        List<Expense> expectedList = Arrays.asList(testExpense);

        // Mock the behavior of the custom repository finder method
        when(expenseRepository.findByExpenseDateBetween(from, to)).thenReturn(expectedList);

        // Call the service method we are actually testing
        List<Expense> actualList = expenseService.findBetween(from, to);

        // Assertions:
        assertEquals(1, actualList.size());
        assertEquals(expectedList.get(0).getDescription(), actualList.get(0).getDescription());

        // Verification: Ensure the findByExpenseDateBetween method was called exactly once with the correct parameters
        verify(expenseRepository).findByExpenseDateBetween(from, to);
    }
}
