package com.ravina.billing_dashboard_backend.repository;
import java.util.List;
import com.ravina.billing_dashboard_backend.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    long countByStatus(String status);

    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Invoice i WHERE i.status = 'PAID'")
    Double getTotalRevenue();
    List<Invoice> findTop5ByOrderByIdDesc();

}