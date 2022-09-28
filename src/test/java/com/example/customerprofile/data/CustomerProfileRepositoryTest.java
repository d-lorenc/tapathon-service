package com.example.customerprofile.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CustomerProfileRepositoryTest {

    @Autowired
    private CustomerProfileRepository subject;

    @Test
	void shouldPersistCustomerProfile() {
		var entity = new CustomerProfileEntity()
				.setFirstName("Joe")
				.setLastName("Doe")
				.setEmail("joe.doe@test.com")
				.setId(123L);

		var savedEntity = subject.save(entity);

		var actual = subject.findById(savedEntity.getId());
		assertThat(actual).isPresent();
		var actualEntity = actual.get();
		assertThat(actualEntity).isNotSameAs(entity);
		assertThat(actualEntity.getFirstName()).isEqualTo(entity.getFirstName());
		assertThat(actualEntity.getLastName()).isEqualTo(entity.getLastName());
		assertThat(actualEntity.getEmail()).isEqualTo(entity.getEmail());
	}
}
