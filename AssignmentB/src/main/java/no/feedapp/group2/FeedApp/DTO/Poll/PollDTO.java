package no.feedapp.group2.FeedApp.DTO.Poll;

import lombok.Getter;
import lombok.Setter;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollDTO {
    private long id;
    private long userId;
    private PollStatus status;
    private String question;
    private int timeLimitInMinutes;
    private boolean privatePoll;
    private int redCount;
    private int greenCount;
    private LocalDateTime closingTime;

    public static PollDTO PollToDTO(Poll poll) {
        var dto = new PollDTO();
        dto.setId(poll.getId());
        dto.setUserId(poll.getUser().getUserId());
        dto.setStatus(poll.getStatus());
        dto.setQuestion(poll.getQuestion());
        dto.setTimeLimitInMinutes(poll.getTimeLimitInMinutes());
        dto.setPrivatePoll(poll.isPrivatePoll());
        dto.setRedCount(poll.getRedCount());
        dto.setGreenCount(poll.getGreenCount());
        dto.setClosingTime(poll.getClosingTime());

        return dto;
    }
}
