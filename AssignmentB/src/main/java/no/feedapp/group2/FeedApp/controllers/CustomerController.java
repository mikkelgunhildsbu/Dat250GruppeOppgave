package no.feedapp.group2.FeedApp.controllers;

import no.feedapp.group2.FeedApp.DTO.CustomerDTO;
import no.feedapp.group2.FeedApp.DTO.CustomerUpdateDTO;
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
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerDTO.ConvertToDTO(customer));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){
        Customer customer = customerRepository.findByUserId(Long.parseLong(id));

        if (customer != null){
            return ResponseEntity.ok(CustomerDTO.ConvertToDTO(customer));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerUpdateDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerUpdateDTO updatedCustomer){
        try{
            Customer existingCustomer = customerRepository.findByUserId(Long.parseLong(id));

            if (existingCustomer == null){
                return ResponseEntity.notFound().build();
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

            return ResponseEntity.ok(updatedCustomer);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
        try{
            customerRepository.deleteById(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
