package com.marcingadz.controller;

import com.marcingadz.entity.Customer;
import com.marcingadz.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTApiController {
    private final Service<Customer> customerService;

    @Autowired
    public RESTApiController(Service<Customer> customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getList();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        Customer c = customerService.get(id);
        if (c == null) {
            throw new CustomerNotFoundException("Customer with specified id does not exist. id=" + id);
        }
        return c;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer c) {
        c.setId(0);
        customerService.add(c);
        return c;
    }

    @PutMapping("/customers")
    public Customer editCustomer(@RequestBody Customer c) {
        customerService.add(c);
        return c;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        Customer c = customerService.get(id);
        if (c == null) {
            throw new CustomerNotFoundException("Customer not found - id: " + id);
        }
        customerService.delete(id);
        return "Deleted id: " + id;
    }
}
