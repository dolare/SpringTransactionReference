package com.example.demo.web;

import com.example.demo.service.CustomerJSMSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jms_session_customer")
public class CustomerJMSSessionController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private CustomerJSMSessionService customerJSMSessionService;

    @PostMapping("/message1/listen")
    public void create(@RequestParam String msg) {
        jmsTemplate.convertAndSend("customer:msg1:new", msg);
    }

    @PostMapping("/message1/direct")
    public void handle(@RequestParam String msg) {
        customerJSMSessionService.handler(msg);
    }

    @GetMapping("/message1")
    public String read() {
        Object obj = jmsTemplate.receiveAndConvert("customer:msg1:reply");
        return String.valueOf(obj);
    }
}
