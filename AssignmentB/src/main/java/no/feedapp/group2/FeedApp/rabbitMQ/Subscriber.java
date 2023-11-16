package no.feedapp.group2.FeedApp.rabbitMQ;

import com.azure.cosmos.CosmosException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import no.feedapp.group2.FeedApp.Cosmos.CosmosApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Subscriber {
    private static final String EXCHANGE_NAME = "logs";
    private static final Logger logger = LoggerFactory.getLogger(CosmosApp.class.getSimpleName());

    public void Subscriber() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String[] list = message.split(",");
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

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}
















