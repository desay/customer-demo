package com.customer.demo.service;

import com.customer.demo.entity.Customer;
import com.customer.demo.entity.CustomerStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public List<Customer> findAll(String stringFilter) {
        return null;
    }

    @Override
    public List<Customer> findAll(String stringFilter, int start, int maxresults) {
        return null;
    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void ensureTestData() {
        if (findAll().isEmpty()) {
            final String[] names = new String[] { "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                    "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                    "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                    "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                    "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                    "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                    "Jaydan Jackson", "Bernard Nilsen" };
            Random r = new Random(0);
            for (String name : names) {
                String[] split = name.split(" ");
                Customer c = new Customer();
                c.setFirstName(split[0]);
                c.setLastName(split[1]);
                c.setStatus(CustomerStatus.values()[r.nextInt(CustomerStatus.values().length)]);
                c.setBirthDate(LocalDate.now().minusDays(r.nextInt(365*100)));
                save(c);
            }
        }
    }
}
