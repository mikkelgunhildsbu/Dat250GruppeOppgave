package no.feedapp.group2.FeedApp.controllers.exceptions;

public class PollClosedException extends Throwable {
    public PollClosedException(long pollId) {
        super("Poll with id " + pollId + " has been closed");
    }
}
