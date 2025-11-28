package com.bites.inventory.Financials.service;

import com.bites.inventory.Financials.dto.SaleInvoiceItemRequest;
import com.bites.inventory.Financials.dto.SaleInvoiceRequest;
import com.bites.inventory.Financials.entity.SaleInvoice;
import com.bites.inventory.Financials.entity.SaleInvoiceItem;
import com.bites.inventory.Financials.repository.SaleInvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class SaleInvoiceService {

    private final SaleInvoiceRepository saleInvoiceRepository;

    public SaleInvoiceService(SaleInvoiceRepository saleInvoiceRepository) {
        this.saleInvoiceRepository = saleInvoiceRepository;
    }

    @Transactional
    public SaleInvoice createInvoice(SaleInvoiceRequest request) {
        SaleInvoice invoice = new SaleInvoice();
        invoice.setInvoiceDateTime(LocalDateTime.now());

        invoice.setCustomerName(request.getCustomerName());
        invoice.setCustomerMobile(request.getCustomerMobile());
        invoice.setOrderType(request.getOrderType());
        invoice.setPaymentMode(request.getPaymentMode());
        invoice.setGstRate(request.getGstRate());

        BigDecimal discount = request.getDiscount() != null ? request.getDiscount() : BigDecimal.ZERO;
        invoice.setDiscount(discount);

        BigDecimal subTotal = BigDecimal.ZERO;

        if (request.getItems() != null) {
            for (SaleInvoiceItemRequest itemReq : request.getItems()) {
                SaleInvoiceItem item = new SaleInvoiceItem();

                // --- MAP THE NAME HERE ---
                item.setDishName(itemReq.getDishName());

                item.setUnit(itemReq.getUnit());
                item.setQuantity(itemReq.getQuantity());
                item.setRate(itemReq.getRate());

                BigDecimal lineTotal = itemReq.getRate().multiply(itemReq.getQuantity());
                item.setLineTotal(lineTotal);

                invoice.addItem(item);
                subTotal = subTotal.add(lineTotal);
            }
        }

        // Calculations
        BigDecimal taxable = subTotal.subtract(discount);
        if (taxable.compareTo(BigDecimal.ZERO) < 0) taxable = BigDecimal.ZERO;
        invoice.setTaxableAmount(taxable);

        BigDecimal gstAmount = taxable.multiply(request.getGstRate())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        invoice.setGstAmount(gstAmount);

        BigDecimal exactTotal = taxable.add(gstAmount);
        BigDecimal finalTotal = exactTotal.setScale(0, RoundingMode.HALF_UP);
        BigDecimal roundOff = finalTotal.subtract(exactTotal);

        invoice.setRoundOffAmount(roundOff);
        invoice.setTotalAmount(finalTotal);

        return saleInvoiceRepository.save(invoice);
    }
}