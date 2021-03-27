package com.marcingadz.dao;

import java.util.List;

public interface DAO<T> {
    T get();
    List<T> getList();
    void update(T obj);
    void delete(T obj);
}
