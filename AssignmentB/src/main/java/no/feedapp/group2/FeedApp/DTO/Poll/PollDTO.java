package no.feedapp.group2.FeedApp.DTO.Poll;

import lombok.Getter;
import lombok.Setter;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerDTO;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;

@Getter
@Setter
public class PollDTO {
    private long userId;
    private PollStatus status;
    private String question;
    private int timeLimitInMinutes;
    private boolean privatePoll;

    public static PollDTO PollToDTO(Poll poll){
        var dto = new PollDTO();
        dto.setUserId(poll.getUser().getUserId());
        dto.setStatus(poll.getStatus());
        dto.setQuestion(poll.getQuestion());
        dto.setTimeLimitInMinutes(poll.getTimeLimitInMinutes());
        dto.setPrivatePoll(poll.isPrivatePoll());

        return dto;
    }


    }
