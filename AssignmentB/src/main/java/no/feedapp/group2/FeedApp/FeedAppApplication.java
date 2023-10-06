package no.feedapp.group2.FeedApp;

import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;
import no.feedapp.group2.FeedApp.domain.Role;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
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
	public CommandLineRunner demo(CustomerRepository cr, PollRepository pr) {
		return (args) -> {
			// save a few customers
			Customer admin1 = new Customer("AdminUser", "admin@feedapp.com", "SuperSecretAdminPassword", Role.ADMIN);
			Customer user1 = new Customer("TestUser1", "test1@test.com", "secret1", Role.VOTER);
			Customer user2 = new Customer("TestUser2", "test2@test.com", "secret2", Role.VOTER);
			Customer user3 = new Customer("TestUser3", "test3@test.com", "secret3", Role.VOTER);
			Customer guest1 = new Customer("GuestUser1", null, null, Role.GUEST);
			Customer guest2 = new Customer("GuestUser2", null, null, Role.GUEST);
			cr.save(admin1);
			cr.save(user1);
			cr.save(user2);
			cr.save(user3);
			cr.save(guest1);
			cr.save(guest2);


			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : cr.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			//Saving a few polls
			pr.save(new Poll(user1, PollStatus.OPEN, "Do you live in norway?", 10, false));

			Poll poll1 = new Poll(user1, PollStatus.CLOSED, "Have you tried parachuting?", 5, true);
			poll1.setGreenCount(10);
			poll1.setRedCount(274);
			pr.save(poll1);


			Poll poll2 = new Poll(user1, PollStatus.CLOSED, "Have you tasted seaweed?", 60, false);
			poll2.setGreenCount(44);
			poll2.setRedCount(51);
			pr.save(poll2);

			Poll poll3 = new Poll(user1, PollStatus.CLOSED, "Do you enjoy hiking?", 20, false);
			poll3.setGreenCount(13);
			poll3.setRedCount(2);
			pr.save(poll3);


			Poll poll4 = new Poll(user2, PollStatus.CLOSED, "Are you religious?", 25, true);
			poll4.setGreenCount(2343);
			poll4.setRedCount(5109);
			pr.save(poll4);


			Poll poll5 = new Poll(user2, PollStatus.CLOSED, "Do you have a university degree?", 55, true);
			poll5.setGreenCount(25);
			poll5.setRedCount(29);
			pr.save(poll5);

			pr.save(new Poll(user3, PollStatus.OPEN, "Are you an engineer?", 5, false));

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
