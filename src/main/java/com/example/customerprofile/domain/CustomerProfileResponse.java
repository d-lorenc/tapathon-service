package com.example.customerprofile.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerProfileResponse {

    @NotNull
    private final Long id;

    private final String firstName;

    private final String lastName;

    @NotBlank
    private final String email;

    public CustomerProfileResponse(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
