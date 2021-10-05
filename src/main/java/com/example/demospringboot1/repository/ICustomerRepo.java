package com.example.demospringboot1.repository;

import com.example.demospringboot1.model.Customer;
import com.example.demospringboot1.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends CrudRepository<Customer,Long> {
    Iterable<Customer> findAllByProvince(Province province);
}
