package com.bites.inventory.Financials.controller;

import com.bites.inventory.Financials.dto.DailySalesReportDTO;
import com.bites.inventory.Financials.dto.MonthlyReport;
import com.bites.inventory.Financials.service.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List; // <--- This import was missing before

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/monthly")
    public MonthlyReport getMonthly(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return reportService.getMonthlyReport(from, to);
    }

    @GetMapping("/detailed")
    public List<DailySalesReportDTO> getDetailedReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return reportService.getDetailedDailyReport(from, to);
    }
}