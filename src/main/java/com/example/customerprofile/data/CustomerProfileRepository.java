package com.example.customerprofile.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfileEntity, Long> {
}
