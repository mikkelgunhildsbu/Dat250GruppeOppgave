package no.feedapp.group2.FeedApp;

import no.feedapp.group2.FeedApp.rabbitMQ.Subscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.Thread.sleep;


@SpringBootApplication
public class FeedAppApplication {
    public static void main(String[] args) throws Exception {
        var maxRetryCount = 10;
        var retryCount = 0;

        SpringApplication.run(FeedAppApplication.class, args);

        while (retryCount < maxRetryCount)
            try {
                new Subscriber().Subscriber();
                System.out.println("SUCCESS: Connected to RabbitMQ");
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Failed to connect to RabbitMQ, retrying in 5 seconds...");
                retryCount++;
                sleep(5000);
            }
    }
}
