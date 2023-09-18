package no.feedapp.group2.FeedApp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Statuses {
    @Id
    @GeneratedValue
    private Long statusId;
    private String statusValue;
}
