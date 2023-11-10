package no.feedapp.group2.FeedApp.MockupApi;


import no.feedapp.group2.FeedApp.rabbitMQ.Publisher;

public class PollApi {

    public void updatePoll(String id, String status, String question, String g_count, String r_count) throws Exception {
        if (status.equals("CLOSED")){
            Publisher publisher = new Publisher();
            String message = id + "," + question + "," + g_count + "," + r_count;
            publisher.Publish(message);
        }
    }

}
