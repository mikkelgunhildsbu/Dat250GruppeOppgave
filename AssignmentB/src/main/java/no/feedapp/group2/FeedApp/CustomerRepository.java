package no.feedapp.group2.FeedApp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByEmail(String email);

    Customer findByUserId(long userId);
}
