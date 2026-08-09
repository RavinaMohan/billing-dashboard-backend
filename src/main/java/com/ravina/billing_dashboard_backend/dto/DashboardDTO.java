package com.ravina.billing_dashboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {

    private long totalCustomers;
    private long activeCustomers;
    private long inactiveCustomers;

    private long totalInvoices;
    private long paidInvoices;
    private long pendingInvoices;

    private double totalRevenue;
}