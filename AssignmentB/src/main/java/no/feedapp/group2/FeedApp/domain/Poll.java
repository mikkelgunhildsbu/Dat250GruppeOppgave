package no.feedapp.group2.FeedApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Customer user;

    private PollStatus status;

    private String question;

    private int redCount;

    private int greenCount;

    private int timeLimitInMinutes;

    private boolean privatePoll;
    private LocalDateTime closingTime;
    private LocalDateTime creationTime;

    @PrePersist
    protected void onCreate() {
        this.creationTime = LocalDateTime.now();
        this.closingTime = this.creationTime.plusMinutes(this.timeLimitInMinutes);
    }

    protected Poll() {
    }

    public Poll(Customer user, PollStatus status, String question, int timeLimitInMinutes, boolean privatePoll) {
        this.user = user;
        this.status = status;
        this.question = question;
        this.timeLimitInMinutes = timeLimitInMinutes;
        this.privatePoll = privatePoll;
        this.redCount = 0;
        this.greenCount = 0;
    }
}