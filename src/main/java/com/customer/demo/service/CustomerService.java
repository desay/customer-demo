package com.customer.demo.service;

import com.customer.demo.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    List<Customer> findAll(String stringFilter);
    List<Customer> findAll(String stringFilter, int start, int maxresults);
    void delete(Customer customer);
    void save(Customer customer);

    void ensureTestData();
}
