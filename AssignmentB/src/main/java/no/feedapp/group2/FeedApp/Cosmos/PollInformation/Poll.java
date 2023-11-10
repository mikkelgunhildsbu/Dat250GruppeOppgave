package no.feedapp.group2.FeedApp.Cosmos.PollInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poll {

    private String id;

    private String result;

    private String redCount;

    private String greenCount;

    private String question;
}