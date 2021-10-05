package com.example.demospringboot1.service.customerservice;

import com.example.demospringboot1.model.Customer;
import com.example.demospringboot1.model.Province;

import java.util.List;
import java.util.Optional;

public interface IServiceCustomer {
    List<Customer> showAll();
    void save(Customer customer);
    void delete(long id);
    Optional<Customer> findById(long id);
    Iterable<Customer> findAllByProvince(Province province);
}
