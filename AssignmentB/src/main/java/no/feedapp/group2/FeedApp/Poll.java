package no.feedapp.group2.FeedApp;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
public class Poll {

    protected Poll(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Customer user;

    private String question;

    private int redCount;

    private int greenCount;

    private int timeLimitInMinutes;

    private boolean privatePoll;
}
