package com.ravina.billing_dashboard_backend.service;

import com.ravina.billing_dashboard_backend.exception.ResourceNotFoundException;
import com.ravina.billing_dashboard_backend.model.Invoice;
import com.ravina.billing_dashboard_backend.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice invoice) {

        Invoice existing = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));

        existing.setInvoiceNumber(invoice.getInvoiceNumber());
        existing.setAmount(invoice.getAmount());
        existing.setStatus(invoice.getStatus());

        return invoiceRepository.save(existing);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}