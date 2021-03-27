package com.marcingadz.controller;

import com.marcingadz.dao.CustomerDAO;
import com.marcingadz.dao.DAO;
import com.marcingadz.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final DAO<Customer> dao;

    @Autowired
    public CustomerController(DAO<Customer> dao) {
        this.dao = dao;
    }

    @RequestMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers",dao.getList());
        return "list-customers";
    }
}
