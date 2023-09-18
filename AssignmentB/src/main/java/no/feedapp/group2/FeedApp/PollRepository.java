package no.feedapp.group2.FeedApp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
