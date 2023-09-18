package no.feedapp.group2.FeedApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FeedAppApplication {

	private static final Logger log = LoggerFactory.getLogger(FeedAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FeedAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository cr, PollRepository pr, StatusesRepository sr) {
		return (args) -> {
			// save a few customers
			Customer user1 = new Customer("TestUser", "test@test.com", "secret");
			Customer user2 = new Customer("TestUser2", "test2@test.com", "secret2");
			cr.save(user1);
			cr.save(user2);

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : cr.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = cr.findByUserId(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			Statuses statusOpen = new Statuses("Open");
			Statuses statusClosed = new Statuses("Closed");
			sr.save(statusOpen);
			sr.save(statusClosed);

			// fetch all statuses
			log.info("Statuses found with findAll():");
			log.info("-------------------------------");
			for (Statuses status: sr.findAll()){
				log.info(status.toString());
			}
			log.info("");


			//Saving a few polls
			pr.save(new Poll(user1, statusOpen, "Test question", 10, false));

			// fetch all polls
			log.info("Polls found with findAll():");
			log.info("-------------------------------");
			for(Poll poll: pr.findAll()){
				log.info(poll.toString());
			}
			log.info("");
		};
	}
}
