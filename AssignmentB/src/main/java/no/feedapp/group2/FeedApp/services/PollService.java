package no.feedapp.group2.FeedApp.services;

import jakarta.annotation.Nullable;
import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService implements IPollService{

    private final PollRepository pollRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PollService(PollRepository pollRepository, CustomerRepository customerRepository) {
        this.pollRepository = pollRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Poll createPoll(PollCreateDTO pollCreateDTO) {
        Customer customer = customerRepository.findByUserId(pollCreateDTO.getUserId());
        Poll poll = PollCreateDTO.convertToPoll(pollCreateDTO,customer);
        return pollRepository.save(poll);
    }

    @Nullable
    @Override
    public Poll getPollById(Long id) {
       return pollRepository.getPollById(id);
    }


    @Override
    public List<Poll> getPollsByUserId(Long userId) {
        return pollRepository.findPollsByUserUserId(userId);
    }

    @Nullable
    @Override
    public Poll updatePoll(Long id, PollUpdateDTO pollUpdateDTO) {
        Poll existingPoll = getPollById(id);
        if (existingPoll == null){
            return null;
        }

        existingPoll.setQuestion(pollUpdateDTO.getQuestion());
        existingPoll.setTimeLimitInMinutes(pollUpdateDTO.getTimeLimitInMinutes());
        existingPoll.setGreenCount(pollUpdateDTO.getGreenCount());
        existingPoll.setRedCount(pollUpdateDTO.getRedCount());

        pollRepository.save(existingPoll);

        return existingPoll;
    }

    @Override
    public void deletePoll(Long id) {
        pollRepository.deletePollById(id);
    }

    @Override
    public void deletePollsByUserId(Long userId) {
        pollRepository.deletePollsByUserUserId(userId);
    }
}
