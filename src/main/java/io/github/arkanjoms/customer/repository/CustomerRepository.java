package io.github.arkanjoms.customer.repository;

import io.github.arkanjoms.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
