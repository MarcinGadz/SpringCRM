package com.marcingadz.service;

import java.util.List;

public interface Service<T> {
    public List<T> getList();
    public void add(T obj);
}
