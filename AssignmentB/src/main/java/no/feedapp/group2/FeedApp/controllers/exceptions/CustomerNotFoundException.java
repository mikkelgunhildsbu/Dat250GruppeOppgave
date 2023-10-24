package no.feedapp.group2.FeedApp.controllers.exceptions;

public class CustomerNotFoundException extends Throwable {
    public CustomerNotFoundException(long customerId) {
        super("Customer with id " + customerId + " not found");
    }
}
