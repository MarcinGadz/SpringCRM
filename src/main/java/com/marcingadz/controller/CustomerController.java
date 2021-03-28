package com.marcingadz.controller;

import com.marcingadz.entity.Customer;
import com.marcingadz.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final Service<Customer> customerService;

    @Autowired
    public CustomerController(Service<Customer> customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers",customerService.getList());
        return "list-customers";
    }

    @GetMapping("/showAddForm")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer")Customer customer) {
        customerService.add(customer);
        return "redirect:/customer/list";
    }
}
