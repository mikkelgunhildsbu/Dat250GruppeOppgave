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

    protected Statuses(){}
    public Statuses(String statusValue){
        this.statusValue = statusValue;
    }

    @Override
    public String toString() {
        return String.format(
                "Status[id=%d, value='%s']",
                statusId, statusValue);
    }
}
