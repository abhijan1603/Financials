package com.bites.inventory.Financials.controller;

import com.bites.inventory.Financials.entity.Expense;
import com.bites.inventory.Financials.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public Expense create(@RequestBody Expense expense) {
        return service.create(expense);
    }

    @GetMapping
    public List<Expense> list(@RequestParam String from,
                              @RequestParam String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return service.findBetween(fromDate, toDate);
    }

}