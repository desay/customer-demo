package com.customer.demo.service;

import com.customer.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    List<Customer> findAll(String stringFilter);

    void delete(Customer customer);
    void save(Customer customer);

    void ensureTestData();
}
