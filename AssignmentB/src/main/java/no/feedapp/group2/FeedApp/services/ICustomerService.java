package no.feedapp.group2.FeedApp.services;

import jakarta.annotation.Nullable;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO;
import no.feedapp.group2.FeedApp.domain.Customer;

public interface ICustomerService {
    /**
     * A method for creating a customer and persistently store it in the database.
     * @param customer the customer that should be created.
     */
    void createCustomer(Customer customer);

    /**
     * Finds a customer by their id.
     *
     * @param id the customer id.
     * @return A customer if one is found, null otherwise.
     */
    @Nullable
    Customer getCustomerById(Long id);

    /**
     * Updates an existing customer if one is found.
     *
     * @param id The customer id.
     * @param updatedCustomer {@link no.feedapp.group2.FeedApp.DTO.Customer.CustomerUpdateDTO}
     * @return True if operation was executed successful, false otherwise.
     */
    boolean updateCustomer(Long id, CustomerUpdateDTO updatedCustomer);

    /**
     * A method for deleting an existing customer
     *
     * @param id The customer id.
     */
    void deleteCustomer(Long id);
}
