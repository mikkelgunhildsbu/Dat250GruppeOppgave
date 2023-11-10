package no.feedapp.group2.FeedApp.rabbitMQ;

import com.azure.cosmos.CosmosException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import no.feedapp.group2.FeedApp.Cosmos.CosmosApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Subscriber {
    private static final String EXCHANGE_NAME = "logs";
    private static Logger logger = LoggerFactory.getLogger(CosmosApp.class.getSimpleName());

    public void Subscriber() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");


        System.out.println(" [x] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            String[] list = message.split(",");
            System.out.println(" [x] Received '" + message + "'");
            String id = list[0];
            String question = list[1];
            String g_count = list[2];
            String r_count = list[3];

            try {
                CosmosApp p = new CosmosApp();
                p.basicOperations(id, question, g_count, r_count);
            } catch (CosmosException e) {
                logger.error("Failed while executing app.", e);
            } finally {
                logger.info("End of demo, press any key to exit.");
            }
            System.out.println("id: " + id);
            System.out.println("question: " + question);
            System.out.println("g_count: " + g_count);
            System.out.println("r_count: " + r_count);

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
















