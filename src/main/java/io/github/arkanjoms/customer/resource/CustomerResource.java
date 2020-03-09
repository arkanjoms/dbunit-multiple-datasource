package io.github.arkanjoms.customer.resource;

import io.github.arkanjoms.customer.model.Customer;
import io.github.arkanjoms.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerResource {

    private final CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(Pageable pageable) {
        return ResponseEntity.ok(customerRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerRepository.save(customer));
    }
}
