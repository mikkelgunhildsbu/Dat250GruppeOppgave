package no.feedapp.group2.FeedApp.DTO.Poll;


import lombok.Getter;
import lombok.Setter;
import no.feedapp.group2.FeedApp.DTO.Customer.CustomerDTO;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;

@Setter
@Getter
public class PollCreateDTO {
    private long userId;
    private PollStatus status;
    private String question;
    private int timeLimitInMinutes;
    private boolean privatePoll;

    public static Poll convertToPoll(PollCreateDTO pollDTO, Customer customer) {
        return new Poll(customer, pollDTO.status, pollDTO.question, pollDTO.timeLimitInMinutes, pollDTO.privatePoll);
    }
}

