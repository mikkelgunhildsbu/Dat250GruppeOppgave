package no.feedapp.group2.FeedApp.controllers.exceptions;

public class PollNotFoundException extends Throwable {
    public PollNotFoundException(long pollId) {
        super("Poll with id " + pollId + " not found");
    }
}
