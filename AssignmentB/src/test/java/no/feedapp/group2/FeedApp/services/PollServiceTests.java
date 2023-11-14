package no.feedapp.group2.FeedApp.services;

import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.controllers.exceptions.PollClosedException;
import no.feedapp.group2.FeedApp.controllers.exceptions.PollNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;
import no.feedapp.group2.FeedApp.repositories.CustomerRepository;
import no.feedapp.group2.FeedApp.repositories.PollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Disabled
public class PollServiceTests {
    CustomerRepository customerRepository;

    PollRepository pollRepository;

    IPollService pollService;

    @BeforeEach
    void setUp() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        pollRepository = Mockito.mock(PollRepository.class);
        pollService = new PollService(pollRepository, customerRepository);
    }

    @Test
    void createPoll_creates_poll() throws CustomerNotFoundException {
        // Arrange
        var pollCreateDTO = new PollCreateDTO(1L, PollStatus.CLOSED, "Question", 10, true);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(Mockito.mock(Customer.class));

        // Act
        pollService.createPoll(pollCreateDTO);

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).save(Mockito.any(Poll.class));
    }

    @Test
    void createPoll_throws_customer_not_found_exception() {
        // Arrange
        var pollCreateDTO = new PollCreateDTO(1L, PollStatus.CLOSED, "Question", 10, true);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(null);

        // Act
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> pollService.createPoll(pollCreateDTO));

        // Assert
        assert exception.getMessage().contains("Customer with id 1 not found");
    }

    @Test
    void getPollById_throws_error_when_poll_not_found() {
        // Arrange
        when(pollRepository.getPollById(Mockito.anyLong())).thenReturn(null);

        // Act
        PollNotFoundException exception = assertThrows(PollNotFoundException.class, () -> pollService.getPollById(1L));

        // Assert
        assert exception.getMessage().contains("Poll with id 1 not found");
    }

    @Test
    void getPollById_returns_poll_when_found() throws PollNotFoundException {
        // Arrange
        var poll = new Poll(Mockito.mock(Customer.class), PollStatus.CLOSED, "Question", 10, true);
        when(pollRepository.getPollById(Mockito.anyLong())).thenReturn(poll);

        // Act
        Poll result = pollService.getPollById(1L);

        // Assert
        assert result.equals(poll);
    }

    @Test
    void getPollsByUserId_throws_error_when_customer_not_found() {
        // Arrange
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(null);

        // Act
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> pollService.getPollsByUserId(1L));

        // Assert
        assert exception.getMessage().contains("Customer with id 1 not found");
    }

    @Test
    void getPollsByUserId_returns_polls_when_found() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", null);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);

        // Act
        pollService.getPollsByUserId(1L);

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).findPollsByUserUserId(1L);
    }

    @Test
    void updatePoll_throws_error_when_poll_not_found() {
        // Arrange
        when(pollRepository.getPollById(Mockito.anyLong())).thenReturn(null);

        // Act
        PollNotFoundException exception = assertThrows(PollNotFoundException.class, () -> pollService.updatePoll(1L, Mockito.mock(PollUpdateDTO.class)));

        // Assert
        assert exception.getMessage().contains("Poll with id 1 not found");
    }

    @Test
    void updatePoll_updates_poll() throws PollNotFoundException, PollClosedException, Exception {
        // Arrange
        var poll = new Poll(Mockito.mock(Customer.class), PollStatus.OPEN, "Question", 10, true);
        var pollUpdateDTO = new PollUpdateDTO("NewQuestion", PollStatus.CLOSED, 5, 100, 100);
        when(pollRepository.getPollById(Mockito.anyLong())).thenReturn(poll);

        // Act
        var result = pollService.updatePoll(1L, pollUpdateDTO);

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).save(Mockito.any(Poll.class));
        assertEquals("NewQuestion", result.getQuestion());
        assertEquals(PollStatus.CLOSED, result.getStatus());
        assertEquals(5, result.getTimeLimitInMinutes());
        assertEquals(100, result.getGreenCount());
        assertEquals(100, result.getRedCount());
    }

    @Test
    void updatePoll_does_not_update_when_null_or_empty() throws PollNotFoundException, PollClosedException, Exception {
        // Arrange
        var poll = new Poll(Mockito.mock(Customer.class), PollStatus.OPEN, "Question", 10, true);
        poll.setGreenCount(25);
        poll.setRedCount(30);
        var pollUpdateDTO = new PollUpdateDTO(null, null, null, null, null);
        when(pollRepository.getPollById(Mockito.anyLong())).thenReturn(poll);

        // Act
        var result = pollService.updatePoll(1L, pollUpdateDTO);

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).save(Mockito.any(Poll.class));
        assertEquals("Question", result.getQuestion());
        assertEquals(PollStatus.OPEN, result.getStatus());
        assertEquals(10, result.getTimeLimitInMinutes());
        assertEquals(25, result.getGreenCount());
        assertEquals(30, result.getRedCount());
    }

    @Test
    void deletePoll_throws_error_when_poll_not_found() {
        // Arrange
        when(pollRepository.getPollById(Mockito.anyLong())).thenReturn(null);

        // Act
        PollNotFoundException exception = assertThrows(PollNotFoundException.class, () -> pollService.deletePoll(1L));

        // Assert
        assert exception.getMessage().contains("Poll with id 1 not found");
    }

    @Test
    void deletePoll_deletes_poll() throws PollNotFoundException {
        // Arrange
        var poll = new Poll(Mockito.mock(Customer.class), PollStatus.OPEN, "Question", 10, true);
        when(pollRepository.getPollById(Mockito.anyLong())).thenReturn(poll);

        // Act
        pollService.deletePoll(1L);

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).deletePollById(1L);
    }

    @Test
    void deletePollsByUserId_throws_error_when_customer_not_found() {
        // Arrange
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(null);

        // Act
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> pollService.deletePollsByUserId(1L));

        // Assert
        assert exception.getMessage().contains("Customer with id 1 not found");
    }

    @Test
    void deletePollsByUserId_deletes_polls() throws CustomerNotFoundException {
        // Arrange
        var customer = new Customer("name", "Test", "", null);
        when(customerRepository.findByUserId(Mockito.anyLong())).thenReturn(customer);

        // Act
        pollService.deletePollsByUserId(1L);

        // Assert
        Mockito.verify(pollRepository, Mockito.times(1)).deletePollsByUserUserId(1L);
    }
}
