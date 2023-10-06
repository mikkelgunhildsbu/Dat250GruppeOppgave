package no.feedapp.group2.FeedApp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.services.IPollService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PollController {

    private final IPollService pollService;

    public PollController(IPollService pollService) {
        this.pollService = pollService;
    }

    @Operation(summary = "Create a new poll")
    @PostMapping("/poll")
    public ResponseEntity<PollDTO> createPoll(@RequestBody PollCreateDTO pollDTO) {
        Poll poll = pollService.createPoll(pollDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(PollDTO.PollToDTO(poll));
    }

    @Operation(summary = "Get a poll by its id")
    @GetMapping("/poll/{id}")
    public ResponseEntity<PollDTO> getPollById(@Parameter(description = "The poll id") @PathVariable String id) {
        Poll poll = pollService.getPollById(Long.parseLong(id));

        if (poll != null) {
            return ResponseEntity.ok(PollDTO.PollToDTO(poll));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get a list of polls belonging to a given user")
    @GetMapping("/poll")
    public ResponseEntity<List<PollDTO>> getPollByCustomerId(@Parameter(description = "The user id") @RequestParam (name = "userId") long userId) {
        List<Poll> polls = pollService.getPollsByUserId(userId);
        if (!polls.isEmpty()) {
            List<PollDTO> pollDTOs = polls.stream().map(PollDTO::PollToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(pollDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a poll by its id")
    @PutMapping("/poll/{id}")
    public ResponseEntity<PollDTO> updatePoll(@Parameter(description = "The poll id") @PathVariable String id, @RequestBody PollUpdateDTO pollUpdateDTO) {
        var updatedPoll = pollService.updatePoll(Long.parseLong(id), pollUpdateDTO);
        if (updatedPoll == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(PollDTO.PollToDTO(updatedPoll));
    }


    @Operation(summary = "Delete a poll by its id")
    @DeleteMapping("/poll/{id}")
    public ResponseEntity<Void> deletePoll(@Parameter(description = "The poll id") @PathVariable String id) {
        pollService.deletePoll(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Delete all polls belonging to a given user")
    @DeleteMapping("/poll")
    public ResponseEntity<Void> deleteAllPollsBysUserId(@Parameter(description = "The user id") @RequestParam(name = "userId") long userId) {
        pollService.deletePollsByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
