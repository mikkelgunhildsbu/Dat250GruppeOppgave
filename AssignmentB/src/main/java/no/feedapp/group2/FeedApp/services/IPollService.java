package no.feedapp.group2.FeedApp.services;

import jakarta.annotation.Nullable;
import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO;
import no.feedapp.group2.FeedApp.domain.Poll;

import java.util.List;

public interface IPollService {

    /**
     * A method for creating polls
     *
     * @param pollCreateDTO {@link no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO}
     */
    Poll createPoll(PollCreateDTO pollCreateDTO);

    /**
     * Retrieve a poll by its id.
     *
     * @param id the poll id.
     * @return {@link no.feedapp.group2.FeedApp.domain.Poll}
     */
    @Nullable
    Poll getPollById(Long id);

    /**
     * Retrieve a list of polls by a user id.
     *
     * @param userId the user id.
     * @return A list of {@link no.feedapp.group2.FeedApp.domain.Poll}
     */
    List<Poll> getPollsByUserId(Long userId);

    /**
     * A method for updating an existing poll.
     *
     * @param id the poll id.
     * @param pollUpdateDTO {@link no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO}
     * @return The updated poll if the poll existed, othewise null
     */
    @Nullable
    Poll updatePoll(Long id, PollUpdateDTO pollUpdateDTO);

    /**
     * A method for deleting a poll.
     * @param id the poll id.
     */
    void deletePoll(Long id);

    /**
     * A method for deleting all polls for a given user id.
     * @param userId the user id.
     */
    void deletePollsByUserId(Long userId);
}
