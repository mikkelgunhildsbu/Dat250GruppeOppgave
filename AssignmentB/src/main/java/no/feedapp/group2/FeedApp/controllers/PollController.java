package no.feedapp.group2.FeedApp.controllers;

import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import no.feedapp.group2.FeedApp.services.IPollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PollController {

    private final PollRepository pollRepository;
    private final IPollService pollService;

    public PollController(PollRepository pollRepository, IPollService pollService) {
        this.pollRepository = pollRepository;
        this.pollService = pollService;
    }

    @PostMapping("/poll")
    public ResponseEntity<PollDTO> createPoll(@RequestBody PollCreateDTO pollDTO) {
        Poll poll = pollService.createPoll(pollDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(PollDTO.PollToDTO(poll));
    }

    @GetMapping("/poll/{id}")
    public ResponseEntity<PollDTO> getPollById(@PathVariable String id) {
        Poll poll = pollService.getPollById(Long.parseLong(id));

        if (poll != null) {
            return ResponseEntity.ok(PollDTO.PollToDTO(poll));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/poll")
    public ResponseEntity<List<PollDTO>> getPollByCustomerId(@RequestParam (name = "userId") long userId) {
        List<Poll> polls = pollService.getPollsByUserId(userId);
        if (!polls.isEmpty()) {
            List<PollDTO> pollDTOs = polls.stream().map(PollDTO::PollToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(pollDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/poll/{id}")
    public ResponseEntity<PollDTO> updatePoll(@PathVariable String id, @RequestBody PollUpdateDTO pollUpdateDTO) {
        var updatedPoll = pollService.updatePoll(Long.parseLong(id), pollUpdateDTO);
        if (updatedPoll == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(PollDTO.PollToDTO(updatedPoll));
    }


    @DeleteMapping("/poll/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable String id) {
        pollService.deletePoll(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/poll")
    public ResponseEntity<Void> deleteAllPollsBysUserId(@RequestParam(name = "userId") long userId) {
        pollService.deletePollsByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
