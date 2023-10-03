package no.feedapp.group2.FeedApp.controllers;

import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PollController {

    private final PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @PostMapping("/poll")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        pollRepository.save(poll);
        return ResponseEntity.status(HttpStatus.CREATED).body(poll);
    }

    @GetMapping("/poll/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable String id) {
        Poll poll = pollRepository.findById(Long.parseLong(id));

        if (poll != null) {
            return ResponseEntity.ok(poll);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Poll> getPollByCustomerId() {
        return null;
    }


    @PutMapping("/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable String id, @RequestBody Poll updatePoll) {
        try {
            Poll existingPoll = pollRepository.findById(Long.parseLong(id));
            if (existingPoll != null) {
                existingPoll.setQuestion(updatePoll.getQuestion());
                existingPoll.setTimeLimitInMinutes(updatePoll.getTimeLimitInMinutes());

                return new ResponseEntity<>(existingPoll, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(existingPoll, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Poll> deletePoll(@PathVariable String id) {
        try {
            pollRepository.deletePollById(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
