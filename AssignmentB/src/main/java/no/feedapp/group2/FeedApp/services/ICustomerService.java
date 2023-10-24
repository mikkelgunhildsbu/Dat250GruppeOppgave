package no.feedapp.group2.FeedApp.services;

import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;

public interface ICustomerService {
    /**
     * A method for creating a customer and persistently store it in the database.
     *
     * @param customer the customer that should be created.
     */
    void createCustomer(Customer customer);

    /**
     * Finds a customer by their id.
     *
     * @param id the customer id.
     * @return A customer if one is found, null otherwise.
     */
    Customer getCustomerById(Long id) throws CustomerNotFoundException;

    /**
     * Updates an existing customer if one is found.
     *
     * @param id              The customer id.
     * @param updatedCustomer {@link no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO}
     * @return The updated customer if operation was executed successful, throws error if customer not found.
     */
    Customer updateCustomer(Long id, CustomerUpdateDTO updatedCustomer) throws CustomerNotFoundException;

    /**
     * A method for deleting an existing customer
     *
     * @param id The customer id.
     */
    void deleteCustomer(Long id) throws CustomerNotFoundException;
}
