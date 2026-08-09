package com.ravina.billing_dashboard_backend.controller;

import com.ravina.billing_dashboard_backend.model.Customer;
import com.ravina.billing_dashboard_backend.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import com.ravina.billing_dashboard_backend.dto.CustomerDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<CustomerDTO> getAllCustomers(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return customerService.getAllCustomers(
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/status")
    public Page<CustomerDTO> getCustomersByStatus(

            @RequestParam String status,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String direction) {

        return customerService.getCustomersByStatus(
                status,
                page,
                size,
                sortBy,
                direction
        );
    }
    @PostMapping
    public Customer addCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id,
                                   @Valid @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}