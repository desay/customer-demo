package com.customer.demo.service;

import com.customer.demo.model.Customer;
import com.customer.demo.model.CustomerStatus;
import com.customer.demo.numbers.Loggable;
import com.customer.demo.numbers.ThirteenDigits;
import com.customer.demo.numbers.generator.NumberGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;

@Loggable
@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    private static CustomerService instance;
    private final HashMap<Long, Customer> contacts = new HashMap<>();
    private long nextId = 0;

    private Comparator comparator = (Comparator<Customer>) (o1, o2) -> (int) (o2.getId() - o1.getId());

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

//
//    public static CustomerService getInstance() {
//        if (instance == null) {
//            instance = new CustomerServiceImpl();
//            instance.ensureTestData();
//        }
//        return instance;
//    }

    @Override
    public List<Customer> findAll() {
        return findAll(null);
    }

    @Override
    public List<Customer> findAll(String stringFilter) {
        ArrayList<Customer> arrayList = new ArrayList<>();
        for (Customer contact : contacts.values()) {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact);
                }
        }
        Collections.sort(arrayList, comparator);
        return arrayList;
    }


    @Override
    public void delete(Customer customer) {
        contacts.remove(customer.getId());
    }

    @Override
    public void save(Customer customer) {
        if (customer == null) {
            return;
        }
        if (customer.getId() == null) {
            customer.setId(nextId++);
        }
        contacts.put(customer.getId(), customer);
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
                c.setNumber(numberGenerator.generateNumber());
                save(c);
            }
        }
    }
}
