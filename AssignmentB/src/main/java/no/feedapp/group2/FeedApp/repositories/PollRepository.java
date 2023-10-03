package no.feedapp.group2.FeedApp.repositories;

import no.feedapp.group2.FeedApp.domain.Poll;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
