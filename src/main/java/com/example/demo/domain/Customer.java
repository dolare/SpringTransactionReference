package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "customer")
@Getter @Setter @NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name", unique = true)
    private String username;

    private String password;

    private String role;
}
