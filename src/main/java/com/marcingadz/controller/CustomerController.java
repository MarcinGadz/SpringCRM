package com.marcingadz.controller;

import com.marcingadz.entity.Customer;
import com.marcingadz.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/customerUpdate")
    public String updateCustomer(@RequestParam("customerId") int id, Model model) {
        Customer customer = customerService.get(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id, Model model) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}
