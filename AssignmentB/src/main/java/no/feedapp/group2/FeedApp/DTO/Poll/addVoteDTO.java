package no.feedapp.group2.FeedApp.DTO.Poll;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import no.feedapp.group2.FeedApp.domain.Vote;

@Getter
@Setter
public class addVoteDTO {
    private @Valid Vote vote;
}
