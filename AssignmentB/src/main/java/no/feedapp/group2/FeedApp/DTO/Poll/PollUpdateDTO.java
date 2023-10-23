package no.feedapp.group2.FeedApp.DTO.Poll;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class PollUpdateDTO {
    private final String question;
    @Min(0)
    private int timeLimitInMinutes;
    @Min(0)
    private int redCount;
    @Min(0)
    private int greenCount;

    public PollUpdateDTO(String question, int timeLimitInMinutes, int redCount, int greenCount) {
        this.question = question;
        this.timeLimitInMinutes = timeLimitInMinutes;
        this.redCount = redCount;
        this.greenCount = greenCount;
    }
}
