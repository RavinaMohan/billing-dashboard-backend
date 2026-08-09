package com.ravina.billing_dashboard_backend.controller;

import com.ravina.billing_dashboard_backend.dto.DashboardDTO;
import com.ravina.billing_dashboard_backend.service.DashboardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ravina.billing_dashboard_backend.model.Invoice;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardDTO getDashboard() {
        return dashboardService.getDashboard();
    }
    @GetMapping("/recent-invoices")
    public List<Invoice> getRecentInvoices() {
        return dashboardService.getRecentInvoices();
    }
}