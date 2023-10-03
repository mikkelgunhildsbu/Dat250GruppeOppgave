package no.feedapp.group2.FeedApp.repositories;

import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {
    Poll findByPollId(long userId);

    Poll deletePollById(long userId);
}
