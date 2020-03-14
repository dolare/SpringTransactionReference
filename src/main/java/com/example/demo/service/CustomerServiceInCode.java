package com.example.demo.service;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomerServiceInCode {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PlatformTransactionManager transactionManage;

    public Customer create(Customer customer) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();

        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);

        TransactionStatus ts = transactionManage.getTransaction(def);

        try {
            customerRepository.save(customer);
            transactionManage.commit(ts);
            return customer;
        } catch (Exception e) {
            transactionManage.rollback(ts);
            throw e;
        }
    }
}
