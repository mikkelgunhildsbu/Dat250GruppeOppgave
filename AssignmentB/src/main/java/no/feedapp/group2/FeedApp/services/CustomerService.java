package no.feedapp.group2.FeedApp.services;

import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{
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

    @Nullable
    @Override
    public Customer getCustomerById(Long id) {
       return customerRepository.findByUserId(id);
    }

    @Override
    public boolean updateCustomer(Long id, CustomerUpdateDTO updatedCustomer) {
        Customer existingCustomer = customerRepository.findByUserId(id);

        if (existingCustomer == null){
            return false;
        }

        if (updatedCustomer.getUserName() != null){
            existingCustomer.setUserName(updatedCustomer.getUserName());
        }
        if (updatedCustomer.getEmail() != null){
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }
        if (updatedCustomer.getPassword() != null){
            existingCustomer.setPassword(updatedCustomer.getPassword());
        }

        customerRepository.save(existingCustomer);

        return true;
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        pollRepository.deletePollsByUserUserId(id);
        customerRepository.deleteById(id);
    }
}
