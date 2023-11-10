package no.feedapp.group2.FeedApp.services;

import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.controllers.exceptions.PollClosedException;
import no.feedapp.group2.FeedApp.controllers.exceptions.PollNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PollService implements IPollService {

    private final PollRepository pollRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PollService(PollRepository pollRepository, CustomerRepository customerRepository) {
        this.pollRepository = pollRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @PreAuthorize("hasAuthority('CUSTOMER_' + #pollCreateDTO.getUserId()) || hasRole('ADMIN')")
    public Poll createPoll(PollCreateDTO pollCreateDTO) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByUserId(pollCreateDTO.getUserId());
        if (customer == null) {
            throw new CustomerNotFoundException(pollCreateDTO.getUserId());
        }

        Poll poll = PollCreateDTO.convertToPoll(pollCreateDTO, customer);
        return pollRepository.save(poll);
    }

    @Override
    public Poll getPollById(Long id) throws PollNotFoundException {
        var poll = pollRepository.getPollById(id);
        if (poll == null) {
            throw new PollNotFoundException(id);
        }

        return poll;
    }


    @Override
    public List<Poll> getPollsByUserId(Long userId) throws CustomerNotFoundException {
        var customer = customerRepository.findByUserId(userId);
        if (customer == null) {
            throw new CustomerNotFoundException(userId);
        }

        return pollRepository.findPollsByUserUserId(userId);
    }


    @Override
    public Poll updatePoll(Long id, PollUpdateDTO pollUpdateDTO) throws PollNotFoundException, PollClosedException {
        Poll existingPoll = getPollById(id);

        if (!Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), existingPoll.getUser().getEmail())) {
            throw new AccessDeniedException("You are not the owner of this poll");
        }

        if (existingPoll.getStatus() == PollStatus.CLOSED) {
            throw new PollClosedException(id);
        }

        if (!(pollUpdateDTO.getQuestion() == null || pollUpdateDTO.getQuestion().isEmpty())) {
            existingPoll.setQuestion(pollUpdateDTO.getQuestion());
        }
        if (pollUpdateDTO.getStatus() != null) {
            existingPoll.setStatus(pollUpdateDTO.getStatus());
        }
        if (pollUpdateDTO.getTimeLimitInMinutes() != null) {
            existingPoll.setTimeLimitInMinutes(pollUpdateDTO.getTimeLimitInMinutes());
        }
        if (pollUpdateDTO.getGreenCount() != null) {
            existingPoll.setGreenCount(pollUpdateDTO.getGreenCount());
        }
        if (pollUpdateDTO.getRedCount() != null) {
            existingPoll.setRedCount(pollUpdateDTO.getRedCount());
        }

        pollRepository.save(existingPoll);

        return existingPoll;
    }

    @Override
    public void deletePoll(Long id) throws PollNotFoundException {
        var poll = pollRepository.getPollById(id);

        if (poll == null) throw new PollNotFoundException(id);

        if (!Objects.equals(SecurityContextHolder.getContext().getAuthentication().getName(), poll.getUser().getEmail())) {
            throw new AccessDeniedException("You are not the owner of this poll");
        }
        pollRepository.deletePollById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('CUSTOMER_' + #userId) || hasRole('ADMIN')")
    public void deletePollsByUserId(@P("userId") Long userId) throws CustomerNotFoundException {
        var customer = customerRepository.findByUserId(userId);
        if (customer == null) throw new CustomerNotFoundException(userId);

        pollRepository.deletePollsByUserUserId(userId);


    }


    /*@Transactional
    public void closeExpiredPolls() {
        List<Poll> openPolls = pollRepository.findAllByStatus(PollStatus.valueOf("OPEN"));

        LocalDateTime now = LocalDateTime.now();

        for (Poll poll : openPolls) {
            if (now.isAfter(poll.getClosingTime())) {
                poll.setStatus(PollStatus.valueOf("CLOSED"));
            }
        }

        pollRepository.saveAll(openPolls);
    }*/
}
