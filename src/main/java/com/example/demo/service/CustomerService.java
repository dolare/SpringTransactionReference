package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}
