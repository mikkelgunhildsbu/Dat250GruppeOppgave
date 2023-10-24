package no.feedapp.group2.FeedApp.services;

import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Role;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CustomerServiceTests {
    CustomerRepository customerRepository;
    PollRepository pollRepository;
    ICustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        pollRepository = Mockito.mock(PollRepository.class);
        customerService = new CustomerService(customerRepository, pollRepository);
    }

    @Test
    void createCustomer_creates_customer() {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.GUEST);

        // Act
        customerService.createCustomer(customer);

        // Assert
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
    }

    @Test
    void getCustomerById_throws_error_when_customer_not_found() {
        // Arrange
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(null);

        // Act
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));

        // Assert
        assert exception.getMessage().contains("Customer with id 1 not found");
    }

    @Test
    void getCustomerById_returns_customer_when_found() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.GUEST);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);

        // Act
        Customer result = customerService.getCustomerById(1L);

        // Assert
        assert Objects.equals(result, customer);
    }

    @Test
    void updateCustomer_throws_error_when_customer_not_found() {
        // Arrange
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(null);

        // Act
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(1L, Mockito.any(CustomerUpdateDTO.class)));

        // Assert
        assert exception.getMessage().contains("Customer with id 1 not found");
    }

    @Test
    void updateCustomer_updates_customer_when_found() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.GUEST);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Customer result = customerService.updateCustomer(1L, new CustomerUpdateDTO("newName", "newEmail", "newPassword"));

        // Assert
        assert Objects.equals(result.getUserName(), "newName");
        assert Objects.equals(result.getEmail(), "newEmail");
        assert Objects.equals(result.getPassword(), "newPassword");
    }

    @Test
    void updateCustomer_with_nullOrEmpty_name_does_not_update_name() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.GUEST);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Customer resultEmpty = customerService.updateCustomer(1L, new CustomerUpdateDTO("", "newEmail", "newPassword"));
        Customer resultNull = customerService.updateCustomer(1L, new CustomerUpdateDTO(null, "newEmail", "newPassword"));

        // Assert
        assert Objects.equals(resultEmpty.getUserName(), "name");
        assert Objects.equals(resultEmpty.getEmail(), "newEmail");
        assert Objects.equals(resultEmpty.getPassword(), "newPassword");

        assert Objects.equals(resultNull.getUserName(), "name");
        assert Objects.equals(resultNull.getEmail(), "newEmail");
        assert Objects.equals(resultNull.getPassword(), "newPassword");
    }

    @Test
    void updateCustomer_with_nullOrEmpty_email_does_not_update_email() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.GUEST);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Customer resultEmpty = customerService.updateCustomer(1L, new CustomerUpdateDTO("newName", "", "newPassword"));
        Customer resultNull = customerService.updateCustomer(1L, new CustomerUpdateDTO("newName", null, "newPassword"));

        // Assert
        assert Objects.equals(resultEmpty.getUserName(), "newName");
        assert Objects.equals(resultEmpty.getEmail(), "Test");
        assert Objects.equals(resultEmpty.getPassword(), "newPassword");

        assert Objects.equals(resultNull.getUserName(), "newName");
        assert Objects.equals(resultNull.getEmail(), "Test");
        assert Objects.equals(resultNull.getPassword(), "newPassword");
    }

    @Test
    void updateCustomer_with_nullOrEmpty_password_does_not_update_password() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "oldPassword", Role.GUEST);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);
        when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Customer resultEmpty = customerService.updateCustomer(1L, new CustomerUpdateDTO("newName", "newEmail", ""));
        Customer resultNull = customerService.updateCustomer(1L, new CustomerUpdateDTO("newName", "newEmail", null));

        // Assert
        assert Objects.equals(resultEmpty.getUserName(), "newName");
        assert Objects.equals(resultEmpty.getEmail(), "newEmail");
        assert Objects.equals(resultEmpty.getPassword(), "oldPassword");

        assert Objects.equals(resultNull.getUserName(), "newName");
        assert Objects.equals(resultNull.getEmail(), "newEmail");
        assert Objects.equals(resultNull.getPassword(), "oldPassword");
    }

    @Test
    void deleteCustomer_throws_error_when_customer_not_found() {
        // Arrange
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(null);

        // Act
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(1L));

        // Assert
        assert exception.getMessage().contains("Customer with id 1 not found");
    }

    @Test
    void deleteCustomer_deletes_customer_when_found() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.GUEST);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);

        // Act
        customerService.deleteCustomer(1L);

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).deletePollsByUserUserId(1L);
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deleteCustomer_is_transactional() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.GUEST);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);
        Mockito.doThrow(new RuntimeException()).when(pollRepository).deletePollsByUserUserId(Mockito.anyLong());

        // Act
        try {
            customerService.deleteCustomer(1L);
        } catch (RuntimeException ignored) {
        }

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).deletePollsByUserUserId(1L);
        Mockito.verify(customerRepository, Mockito.times(0)).deleteById(1L);
    }
}
