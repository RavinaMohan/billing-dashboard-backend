package com.ravina.billing_dashboard_backend.service;
import com.ravina.billing_dashboard_backend.model.Invoice;
import java.util.List;
import com.ravina.billing_dashboard_backend.dto.DashboardDTO;
import com.ravina.billing_dashboard_backend.repository.CustomerRepository;
import com.ravina.billing_dashboard_backend.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final InvoiceRepository invoiceRepository;

    public DashboardService(CustomerRepository customerRepository,
                            InvoiceRepository invoiceRepository) {

        this.customerRepository = customerRepository;
        this.invoiceRepository = invoiceRepository;
    }


    public DashboardDTO getDashboard() {

        long totalCustomers = customerRepository.count();

        long activeCustomers =
                customerRepository.countByStatus("ACTIVE");

        long inactiveCustomers =
                totalCustomers - activeCustomers;

        long totalInvoices =
                invoiceRepository.count();

        long paidInvoices =
                invoiceRepository.countByStatus("PAID");

        long pendingInvoices =
                totalInvoices - paidInvoices;

        double totalRevenue =
                invoiceRepository.getTotalRevenue();

        return new DashboardDTO(
                totalCustomers,
                activeCustomers,
                inactiveCustomers,
                totalInvoices,
                paidInvoices,
                pendingInvoices,
                totalRevenue
        );
    }


    public List<Invoice> getRecentInvoices() {
        return invoiceRepository.findTop5ByOrderByIdDesc();
    }
}
