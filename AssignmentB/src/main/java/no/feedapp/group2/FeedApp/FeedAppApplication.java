package no.feedapp.group2.FeedApp;

import no.feedapp.group2.FeedApp.rabbitMQ.Subscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FeedAppApplication {
    public static void main(String[] args) throws Exception {
        new Subscriber().Subscriber();
        SpringApplication.run(FeedAppApplication.class, args);
    }
}
