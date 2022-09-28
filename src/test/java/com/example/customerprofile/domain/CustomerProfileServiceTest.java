package com.example.customerprofile.domain;

import com.example.customerprofile.data.CustomerProfileEntity;
import com.example.customerprofile.data.CustomerProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerProfileServiceTest {

    @Mock
    private CustomerProfileRepository repository;

    @InjectMocks
    private CustomerProfileService subject;

    @Test
    void shouldDelegateToRepositoryToPersistProfile() {
        var customerProfile = new CustomerProfileCreateRequest("Joe", "Doe", "joe.doe@test.org");
        when(repository.save(any())).thenAnswer((invocation) -> invocation.getArgument(0));

        var result = subject.create(customerProfile);

        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo(customerProfile.getFirstName());
        assertThat(result.getLastName()).isEqualTo(customerProfile.getLastName());
        assertThat(result.getEmail()).isEqualTo(customerProfile.getEmail());

        var customerProfileEntityCaptor = ArgumentCaptor.forClass(CustomerProfileEntity.class);
        verify(repository).save(customerProfileEntityCaptor.capture());

        var customerProfileEntity = customerProfileEntityCaptor.getValue();
        assertThat(customerProfileEntity.getFirstName()).isEqualTo(customerProfile.getFirstName());
        assertThat(customerProfileEntity.getLastName()).isEqualTo(customerProfile.getLastName());
        assertThat(customerProfileEntity.getEmail()).isEqualTo(customerProfile.getEmail());
        assertThat(customerProfileEntity.getId()).isEqualTo(result.getId());
    }

    @Test
    void shouldDelegateToRepositoryToRetrieveProfile() {
        var entity = new CustomerProfileEntity().setId(123L).setFirstName("Joe").setLastName("Doe").setEmail("joe.doe@test.org");
        when(repository.findById(any())).thenReturn(Optional.of(entity));

        var resultOptional = subject.getById(123L);

        assertThat(resultOptional).isPresent();
        var result = resultOptional.get();
        assertThat(result.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(result.getLastName()).isEqualTo(entity.getLastName());
        assertThat(result.getEmail()).isEqualTo(entity.getEmail());
        assertThat(result.getId()).isEqualTo(123L);
        verify(repository).findById(123L);
    }
}
