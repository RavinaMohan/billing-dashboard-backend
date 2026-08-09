package com.ravina.billing_dashboard_backend.repository;

import com.ravina.billing_dashboard_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long>,
        JpaSpecificationExecutor<Customer> {

    List<Customer> findByNameContainingIgnoreCase(String name);
    long countByStatus(String status);

    Page<Customer> findByStatus(String status, Pageable pageable);

}