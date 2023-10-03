package no.feedapp.group2.FeedApp.controllers;

import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
        Customer customer = customerRepository.findByUserId(Long.parseLong(id));

        if (customer != null){
            return ResponseEntity.ok(customer);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
