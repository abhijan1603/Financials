package com.bites.inventory.Financials.controller;

import com.bites.inventory.Financials.entity.Expense;
import com.bites.inventory.Financials.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc; // Used to simulate web requests

    @Autowired
    private ObjectMapper objectMapper; // Helper for converting objects to JSON strings

    // This replaces the real service with a Mockito mock instance
    @MockBean
    private ExpenseService expenseService;

    private Expense testExpense;

    @BeforeEach
    public void setUp() {
        testExpense = new Expense();
        testExpense.setId(1L);
        testExpense.setDescription("Groceries");

        // FIX 1: Use BigDecimal instead of double
        testExpense.setAmount(new BigDecimal("50.00"));

        // FIX 2: Check the exact name of the method/field in your Expense entity
        // If your method is actually setExpenseDate, change the line below:
        // testExpense.setExpenseDate(LocalDate.now());
        // If it is setDate, the import should be correct:
        testExpense.setExpenseDate(LocalDate.now());
    }


    // --- Test Cases ---

    @Test
    public void createExpenseShouldReturnSavedExpense() throws Exception {
        // Mock the service behavior: when create() is called with any expense, return our test expense
        when(expenseService.create(any(Expense.class))).thenReturn(testExpense);

        // Perform a POST request to /api/expenses with the test object as JSON
        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testExpense)))

                // Assert the response status is 200 OK
                .andExpect(status().isOk())
                // Assert the response body matches the returned object's description and ID
                .andExpect(jsonPath("$.description").value("Groceries"))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void listExpensesBetweenDatesShouldReturnFilteredList() throws Exception {
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 1, 31);
        List<Expense> mockList = Arrays.asList(testExpense);

        // Mock the service behavior: when findBetween() is called, return our list
        when(expenseService.findBetween(from, to)).thenReturn(mockList);

        // Perform a GET request to /api/expenses with 'from' and 'to' request parameters
        mockMvc.perform(get("/api/expenses")
                        .param("from", from.toString())
                        .param("to", to.toString()))

                // Assert the response status is 200 OK
                .andExpect(status().isOk())
                // Assert the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the JSON array has one item and the description matches
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Groceries")); // Corrected JSON path
    }

    @Test
    public void listExpensesShouldReturnBadRequestWhenDatesAreMissing() throws Exception {
        // Test behavior when required parameters are not provided
        mockMvc.perform(get("/api/expenses"))
                .andExpect(status().isBadRequest()); // Expect HTTP 400 Bad Request
    }
}
