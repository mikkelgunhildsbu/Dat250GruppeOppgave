package no.feedapp.group2.FeedApp.repositories;

import no.feedapp.group2.FeedApp.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUserId(long userId);

    Optional<Customer> findByUserName(String username);
}
