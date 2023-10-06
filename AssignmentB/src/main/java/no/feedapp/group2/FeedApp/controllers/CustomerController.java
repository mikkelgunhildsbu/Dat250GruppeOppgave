package no.feedapp.group2.FeedApp.controllers;

import no.feedapp.group2.FeedApp.DTO.Customer.CustomerDTO;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.services.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerDTO.ConvertToDTO(customer));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){
        Customer customer = customerService.getCustomerById(Long.parseLong(id));

        if (customer == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(CustomerDTO.ConvertToDTO(customer));
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerUpdateDTO> updateCustomer(@PathVariable String id, @RequestBody CustomerUpdateDTO updatedCustomer){
        var result = customerService.updateCustomer(Long.parseLong(id), updatedCustomer);
        if (!result){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }
}
