package com.example.demo.web;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceInCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;

    @Autowired
    CustomerServiceInCode customerServiceInCode;

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/createincode")
    public Customer createIncode(@RequestBody Customer customer) {
        return customerServiceInCode.create(customer);
    }

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("")
    public List<Customer> getAll () {
        return customerRepository.findAll();
    }


}
