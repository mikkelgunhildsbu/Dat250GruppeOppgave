package no.feedapp.group2.FeedApp.rabbitMQ;

public class SubPubMain {

    public static void main(String[] args) throws Exception{
        Publisher publisher = new Publisher();
        Subscriber subscriber = new Subscriber();

        subscriber.Subscriber();
        publisher.Publish("I have sent you a message!");


    }
}

