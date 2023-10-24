package no.feedapp.group2.FeedApp.services;

import jakarta.transaction.Transactional;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final PollRepository pollRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PollRepository pollRepository) {
        this.customerRepository = customerRepository;
        this.pollRepository = pollRepository;
    }

    @Override
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        var customer = customerRepository.findByUserId(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        return customer;
    }

    @Override
    public Customer updateCustomer(Long id, CustomerUpdateDTO updatedCustomer) throws CustomerNotFoundException {
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
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        var customer = customerRepository.findByUserId(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        pollRepository.deletePollsByUserUserId(id);
        customerRepository.deleteById(id);
    }
}
