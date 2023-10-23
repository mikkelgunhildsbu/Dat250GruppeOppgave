package no.feedapp.group2.FeedApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerDTO;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Role;
import no.feedapp.group2.FeedApp.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void GetCustomer_With_Existing_Id_Returns_Customer() throws Exception, CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", Role.VOTER);
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/customer/1"));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(CustomerDTO.ConvertToDTO(customer))));
    }

    @Test
    void GetCustomer_With_Non_Existing_Id_Returns_Not_Found() throws Exception, CustomerNotFoundException {
        // Arrange
        when(customerService.getCustomerById(1L)).thenThrow(new CustomerNotFoundException(1L));

        // Act
        ResultActions resultActions = mockMvc.perform(get("/customer/1"));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void GetCustomer_With_Invalid_Id_Returns_Bad_Request() throws Exception {
        // Arrange

        // Act
        ResultActions resultActionsString = mockMvc.perform(get("/customer/invalid"));
        ResultActions resultActionsSymbols = mockMvc.perform(get("/customer/.#$'@e"));
        ResultActions resultActionsNegativeInteger = mockMvc.perform(get("/customer/-1"));
        ResultActions resultActionsZero = mockMvc.perform(get("/customer/0"));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
        resultActionsSymbols.andExpect(status().isBadRequest());
        resultActionsZero.andExpect(status().isBadRequest());
        resultActionsNegativeInteger.andExpect(status().isBadRequest());
    }

    @Test
    void PostCustomer_With_Valid_Customer_Returns_Created() throws Exception {
        // Arrange
        var customer = new Customer("name", "Test@test.com", "test", Role.ADMIN);

        // Act
        ResultActions resultActions = mockMvc.perform(
                post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)));

        // Assert
        resultActions.andExpect(status().isCreated())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(CustomerDTO.ConvertToDTO(customer))));
    }

    @Test
    void PostCustomer_With_Invalid_Email_Returns_Bad_Request() throws Exception {
        // Arrange
        var customer = new Customer("name", "Test", "test", Role.GUEST);

        // Act
        ResultActions resultActions = mockMvc.perform(
                post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)));

        // Assert
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void PostCustomer_With_Invalid_Password_Returns_Bad_Request() throws Exception {
        // Arrange
        var customer = new Customer("name", "test@test.com", "", Role.GUEST);

        // Act
        ResultActions resultActions = mockMvc.perform(
                post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)));

        // Assert
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void PostCustomer_With_Invalid_UserName_Returns_Bad_Request() throws Exception {
        // Arrange
        var customer = new Customer("", "test@test.com", "test", Role.GUEST);

        // Act
        ResultActions resultActions = mockMvc.perform(
                post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customer)));

        // Assert
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void UpdateCustomer_With_Valid_Customer_Returns_Ok() throws Exception, CustomerNotFoundException {
        // Arrange
        var customerUpdateDTO = new CustomerUpdateDTO("name", "test@test.com", "test");

        var updatedCustomer = new Customer("name", "test@test.com", "test", Role.GUEST);
        updatedCustomer.setUserId(1L);

        when(customerService.updateCustomer(Mockito.anyLong(), Mockito.any(CustomerUpdateDTO.class))).thenReturn(updatedCustomer);

        // Act
        ResultActions resultActions = mockMvc.perform(
                put("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerUpdateDTO)));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(CustomerDTO.ConvertToDTO(updatedCustomer))));
    }

    @Test
    void UpdateCustomer_With_Invalid_Id_Returns_Bad_Request() throws Exception {
        // Arrange
        var customerDTO = new CustomerUpdateDTO("name", "mail@mail.com", "test");

        // Act
        ResultActions resultActionsString = mockMvc.perform(
                put("/customer/invalid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerDTO)));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
    }

    @Test
    void UpdateCustomer_With_Invalid_Email_Returns_Bad_Request() throws Exception {
        // Arrange
        var customerDTO = new CustomerUpdateDTO("name", "mail", "test");

        // Act
        ResultActions resultActionsString = mockMvc.perform(
                put("/customer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerDTO)));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
    }

    @Test
    void DeleteCustomer_With_Valid_Id_Returns_No_Content() throws Exception {
        // Arrange

        // Act
        ResultActions resultActions = mockMvc.perform(delete("/customer/1"));

        // Assert
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    void DeleteCustomer_When_Customer_Does_Not_Exist_Returns_Not_Found() throws Exception, CustomerNotFoundException {
        // Arrange
        doThrow(new CustomerNotFoundException(1L)).when(customerService).deleteCustomer(1L);

        // Act
        ResultActions resultActions = mockMvc.perform(delete("/customer/1"));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }
}

