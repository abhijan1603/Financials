package com.bites.inventory.Financials.service;

import com.bites.inventory.Financials.entity.Expense;
import com.bites.inventory.Financials.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense create(Expense expense) {
        return repository.save(expense);
    }

    public List<Expense> findBetween(LocalDate from, LocalDate to) {
        return repository.findByExpenseDateBetween(from, to);
    }

}