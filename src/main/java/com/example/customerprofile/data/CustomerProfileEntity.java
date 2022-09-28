package com.example.customerprofile.data;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "CUSTOMER_PROFILE")
public class CustomerProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    public Long getId() {
        return id;
    }

    public CustomerProfileEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerProfileEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerProfileEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerProfileEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
