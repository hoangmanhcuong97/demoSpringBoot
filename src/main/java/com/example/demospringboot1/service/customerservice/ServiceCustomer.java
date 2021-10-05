package com.example.demospringboot1.service.customerservice;

import com.example.demospringboot1.model.Customer;
import com.example.demospringboot1.model.Province;
import com.example.demospringboot1.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceCustomer implements IServiceCustomer{
    @Autowired
    private ICustomerRepo iCustomerRP;
    @Override
    public List<Customer> showAll() {
        return (List<Customer>)iCustomerRP.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRP.save(customer);
    }

    @Override
    public void delete(long id) {
        iCustomerRP.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return iCustomerRP.findById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return iCustomerRP.findAllByProvince(province);
    }
}
