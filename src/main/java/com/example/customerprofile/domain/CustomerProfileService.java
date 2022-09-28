package com.example.customerprofile.domain;

import com.example.customerprofile.data.CustomerProfileEntity;
import com.example.customerprofile.data.CustomerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CustomerProfileService {

    private final CustomerProfileRepository repository;

    public CustomerProfileService(CustomerProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerProfileResponse create(CustomerProfileCreateRequest dto) {
        var entity = new CustomerProfileEntity()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail());

        var persistedEntity = repository.save(entity);
        return entityToDto(persistedEntity);
    }

    public Optional<CustomerProfileResponse> getById(Long id) {
        return repository.findById(id)
                .map(this::entityToDto);
    }

    private CustomerProfileResponse entityToDto(CustomerProfileEntity entity) {
        return new CustomerProfileResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail());
    }
}
