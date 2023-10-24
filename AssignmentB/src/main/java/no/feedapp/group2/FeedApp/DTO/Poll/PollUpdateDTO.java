package no.feedapp.group2.FeedApp.DTO.Poll;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import no.feedapp.group2.FeedApp.domain.PollStatus;

@Getter
public class PollUpdateDTO {
    private final String question;
    @Valid
    private PollStatus status = PollStatus.OPEN;

    @Min(0)
    private Integer timeLimitInMinutes;

    @Min(0)
    private Integer redCount;

    @Min(0)
    private Integer greenCount;

    public PollUpdateDTO(String question, PollStatus status, Integer timeLimitInMinutes, Integer redCount, Integer greenCount) {
        this.question = question;
        this.status = status;
        this.timeLimitInMinutes = timeLimitInMinutes;
        this.redCount = redCount;
        this.greenCount = greenCount;
    }
}
