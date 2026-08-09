package com.ravina.billing_dashboard_backend.specification;

import com.ravina.billing_dashboard_backend.model.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<Customer> hasStatus(String status) {

        return (root, query, criteriaBuilder) -> {

            if (status == null || status.isEmpty()) {
                return null;
            }

            return criteriaBuilder.equal(root.get("status"), status);

        };
    }
}