package com.marcingadz.dao;

import java.util.List;

public interface DAO<T> {
    T get(int id);
    List<T> getList();
    void update(T obj);
    void delete(int id);
    void add(T obj);
}
