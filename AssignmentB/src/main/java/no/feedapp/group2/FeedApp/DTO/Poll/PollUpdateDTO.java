package no.feedapp.group2.FeedApp.DTO.Poll;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollUpdateDTO {
    private String question;
    private int timeLimitInMinutes;
    private int redCount;
    private int greenCount;
}
