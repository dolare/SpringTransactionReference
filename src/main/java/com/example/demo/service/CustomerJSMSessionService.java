package com.example.demo.service;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerJSMSessionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = "customer:msg1:new", containerFactory = "msgFactory")
    public void handler(String msg) {
        logger.info("GET msg: {}", msg);
        String reply = "reply1-" + msg;
        jmsTemplate.convertAndSend("customer:msg1:reply", reply);

        if (msg.contains("error")) {
            simulateError();
        }
    }

    private void simulateError() {
        throw new RuntimeException("Some Data Error.");
    }
}
