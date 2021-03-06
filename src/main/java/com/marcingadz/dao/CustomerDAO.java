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
    public Customer get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Override
    public List<Customer> getList() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> getCustomersQuery = session.createQuery("From Customer order by lastName"
                , Customer.class);
        return getCustomersQuery.getResultList();
    }

    @Override
    public void add(Customer obj) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }

    @Override
    public void update(Customer obj) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(obj);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer c = session.get(Customer.class, id);
        session.delete(c);
    }
}
