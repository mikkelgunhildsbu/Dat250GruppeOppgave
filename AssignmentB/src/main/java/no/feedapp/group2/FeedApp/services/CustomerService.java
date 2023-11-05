package no.feedapp.group2.FeedApp.services;

import jakarta.transaction.Transactional;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final PollRepository pollRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PollRepository pollRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.pollRepository = pollRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    @PostAuthorize("returnObject.userName == authentication.name || hasRole('ADMIN')")
    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        var customer = customerRepository.findByUserId(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        return customer;
    }

    @Override
    @PreAuthorize("hasAuthority('CUSTOMER_' + #id) || hasRole('ADMIN')")
    public Customer updateCustomer(@P("id") Long id, CustomerUpdateDTO updatedCustomer) throws CustomerNotFoundException {
        Customer existingCustomer = customerRepository.findByUserId(id);

        if (existingCustomer == null) {
            throw new CustomerNotFoundException(id);
        }

        if (notNullOrEmpty(updatedCustomer.getUserName())) {
            existingCustomer.setUserName(updatedCustomer.getUserName());
        }
        if (notNullOrEmpty(updatedCustomer.getEmail())) {
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }
        if (notNullOrEmpty(updatedCustomer.getPassword())) {
            existingCustomer.setPassword(updatedCustomer.getPassword());
        }

        return customerRepository.save(existingCustomer);
    }

    private boolean notNullOrEmpty(String str) {
        return !(str == null || str.isEmpty());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('CUSTOMER_' + #id) || hasRole('ADMIN')")
    public void deleteCustomer(@P("id") Long id) throws CustomerNotFoundException {
        var customer = customerRepository.findByUserId(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        pollRepository.deletePollsByUserUserId(id);
        customerRepository.deleteById(id);
    }
}
