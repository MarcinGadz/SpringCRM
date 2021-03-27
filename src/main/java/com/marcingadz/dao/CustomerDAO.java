package com.marcingadz.dao;

import com.marcingadz.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAO implements DAO<Customer> {
    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Customer get() {
        return null;
    }

    @Override
    @Transactional
    public List<Customer> getList() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> getCustomersQuery = session.createQuery("From Customer", Customer.class);
        return getCustomersQuery.getResultList();
    }

    @Override
    public void update(Customer obj) {

    }

    @Override
    public void delete(Customer obj) {

    }
}
