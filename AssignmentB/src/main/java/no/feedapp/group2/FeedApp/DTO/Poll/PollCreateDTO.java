package no.feedapp.group2.FeedApp.DTO.Poll;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;

@Setter
@Getter
public class PollCreateDTO {
    @Min(1)
    private long userId;
    private @Valid PollStatus status;
    private String question;
    private int timeLimitInMinutes;
    private boolean privatePoll;

    public PollCreateDTO(long userId, PollStatus pollStatus, String question, int timeLimitInMinutes, boolean privatePoll) {
        this.userId = userId;
        this.status = pollStatus;
        this.question = question;
        this.timeLimitInMinutes = timeLimitInMinutes;
        this.privatePoll = privatePoll;
    }

    public static Poll convertToPoll(PollCreateDTO pollDTO, Customer customer) {
        return new Poll(customer, pollDTO.status, pollDTO.question, pollDTO.timeLimitInMinutes, pollDTO.privatePoll);
    }
}

