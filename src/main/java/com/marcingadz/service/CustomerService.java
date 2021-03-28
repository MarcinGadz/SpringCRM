package com.marcingadz.service;

import com.marcingadz.dao.CustomerDAO;
import com.marcingadz.dao.DAO;
import com.marcingadz.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements com.marcingadz.service.Service<Customer> {

    private DAO<Customer> dao;

    @Autowired
    public CustomerService(DAO<Customer> dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<Customer> getList() {
        return dao.getList();
    }

    @Override
    @Transactional
    public void add(Customer obj) {
        dao.add(obj);
    }
}