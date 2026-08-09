package com.ravina.billing_dashboard_backend.service;

import com.ravina.billing_dashboard_backend.exception.ResourceNotFoundException;
import com.ravina.billing_dashboard_backend.model.Customer;
import com.ravina.billing_dashboard_backend.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import com.ravina.billing_dashboard_backend.dto.CustomerDTO;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<CustomerDTO> getAllCustomers(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Customer> customerPage = customerRepository.findAll(pageable);

        return customerPage.map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStatus()
        ));
    }


    public Page<CustomerDTO> getCustomersByStatus(
            String status,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Customer> customerPage =
                customerRepository.findByStatus(status, pageable);

        return customerPage.map(customer -> new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getStatus()
        ));
    }
    public Customer saveCustomer(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setStatus(customerDTO.getStatus());

        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Long id, CustomerDTO customerDTO)  {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        existing.setName(customerDTO.getName());
        existing.setEmail(customerDTO.getEmail());
        existing.setPhone(customerDTO.getPhone());
        existing.setStatus(customerDTO.getStatus());

        return customerRepository.save(existing);
    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}