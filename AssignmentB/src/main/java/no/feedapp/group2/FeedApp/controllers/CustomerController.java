package no.feedapp.group2.FeedApp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerDTO;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.services.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create a new customer")
    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody Customer customer){
        customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerDTO.ConvertToDTO(customer));
    }

    @Operation(summary = "Get a customer by its id")
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@Parameter(description = "The customer id") @PathVariable @Min(1) long id) throws CustomerNotFoundException {
        Customer customer = customerService.getCustomerById(id);

        return ResponseEntity.ok(CustomerDTO.ConvertToDTO(customer));
    }

    @Operation(summary = "Update a customer by its id")
    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@Parameter(description = "The customer id") @PathVariable("id") @Min(1) long id, @Valid @RequestBody CustomerUpdateDTO updatedCustomer) throws CustomerNotFoundException {
        var result = customerService.updateCustomer(id, updatedCustomer);
        return ResponseEntity.ok(CustomerDTO.ConvertToDTO(result));
    }

    @Operation(summary = "Delete a customer by its id")
    @DeleteMapping("customer/{id}")
    public ResponseEntity<String> deleteCustomer(@Parameter(description = "The customer id") @PathVariable long id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
