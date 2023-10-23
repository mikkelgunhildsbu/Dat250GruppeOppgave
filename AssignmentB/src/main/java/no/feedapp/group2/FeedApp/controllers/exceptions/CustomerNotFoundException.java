package no.feedapp.group2.FeedApp.controllers.exceptions;

public class CustomerNotFoundException extends Throwable {
    private String errorMessage;
    public CustomerNotFoundException(long customerId) {
        super("Customer with id " + customerId + " not found");
        this.errorMessage = "Customer with id " + customerId + " not found";
    }
}
