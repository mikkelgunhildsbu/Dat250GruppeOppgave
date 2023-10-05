package no.feedapp.group2.FeedApp.controllers;

import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollDTO;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PollController {

    private final PollRepository pollRepository;
    private final CustomerRepository customerRepository;

    public PollController(PollRepository pollRepository, CustomerRepository customerRepository) {
        this.pollRepository = pollRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/poll")
    public ResponseEntity<PollDTO> createPoll(@RequestBody PollCreateDTO pollDTO) {
        Customer customer = customerRepository.findByUserId(pollDTO.getUserId());
        Poll poll = PollCreateDTO.convertToPoll(pollDTO,customer);
        pollRepository.save(poll);
        return ResponseEntity.status(HttpStatus.CREATED).body(PollDTO.PollToDTO(poll));
    }

    @GetMapping("/poll/{id}")
    public ResponseEntity<PollDTO> getPollById(@PathVariable String id) {
        Poll poll = pollRepository.findById(Long.parseLong(id));

        if (poll != null) {
            return ResponseEntity.ok(PollDTO.PollToDTO(poll));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/poll")
    public ResponseEntity<List<PollDTO>> getPollByCustomerId(@RequestParam (name = "userId") long userId) {
        List<Poll> polls = pollRepository.findPollsByUserUserId(userId);
        if (!polls.isEmpty()) {
            List<PollDTO> pollDTOs = polls.stream().map(PollDTO::PollToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(pollDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PollDTO> updatePoll(@PathVariable String id, @RequestBody Poll updatePoll) {
        try {
            Poll existingPoll = pollRepository.findById(Long.parseLong(id));
            if (existingPoll != null) {
                existingPoll.setQuestion(updatePoll.getQuestion());
                existingPoll.setTimeLimitInMinutes(updatePoll.getTimeLimitInMinutes());
                existingPoll.setGreenCount(updatePoll.getGreenCount());
                existingPoll.setRedCount(updatePoll.getRedCount());

                return new ResponseEntity<>(PollDTO.PollToDTO(existingPoll), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable String id) {
        try {
            pollRepository.deletePollById(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("poll")
    public ResponseEntity<Void> deleteAllPollsBysUserId(@RequestParam(name = "userId") long userId) {
        try {
            pollRepository.deletePollsByUserUserId(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
