package no.feedapp.group2.FeedApp.repositories;

import jakarta.transaction.Transactional;
import no.feedapp.group2.FeedApp.domain.Poll;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PollRepository extends CrudRepository<Poll, Long> {
    Poll getPollById(long Id);

    @Transactional
    void deletePollById(long Id);

    @Transactional
    void deletePollsByUserUserId(long Id);
    List<Poll> findPollsByUserUserId(Long UserId);

    //List<Poll> findAllByStatus(PollStatus pollStatus);
}
