package no.feedapp.group2.FeedApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.feedapp.group2.FeedApp.DTO.Poll.PollCreateDTO;
import no.feedapp.group2.FeedApp.DTO.Poll.PollUpdateDTO;
import no.feedapp.group2.FeedApp.controllers.exceptions.CustomerNotFoundException;
import no.feedapp.group2.FeedApp.controllers.exceptions.PollNotFoundException;
import no.feedapp.group2.FeedApp.domain.Customer;
import no.feedapp.group2.FeedApp.domain.Poll;
import no.feedapp.group2.FeedApp.domain.PollStatus;
import no.feedapp.group2.FeedApp.services.IPollService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PollController.class)
class PollControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPollService pollService;

    @Test
    void createPoll_when_customer_not_found_throws_error() throws Exception, CustomerNotFoundException {
        // Arrange
        var pollCreateDTO = new PollCreateDTO(1L, PollStatus.OPEN, "Question", 1, true);

        doThrow(new CustomerNotFoundException(1L)).when(pollService).createPoll(Mockito.any(PollCreateDTO.class));

        // Act
        ResultActions resultActions = mockMvc.perform(post("/poll")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(pollCreateDTO)));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void createPoll_when_customer_found_returns_created() throws Exception, CustomerNotFoundException {
        // Arrange
        var pollCreateDTO = new PollCreateDTO(1L, PollStatus.OPEN, "Question", 1, true);
        var customer = Mockito.mock(Customer.class);
        var poll = new Poll(customer, PollStatus.OPEN, "Question", 1, true);
        poll.setId(1L);

        when(pollService.createPoll(Mockito.any(PollCreateDTO.class))).thenReturn(poll);

        // Act
        ResultActions resultActions = mockMvc.perform(post("/poll")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(pollCreateDTO)));

        // Assert
        resultActions.andExpect(status().isCreated());
    }

    @Test
    void createPoll_with_invalid_pollCreateDTO_returns_bad_request() throws Exception {
        // Arrange
        var pollCreateDTOWithInvalidUserId = new PollCreateDTO(-1L, PollStatus.OPEN, "Question", 1, true);
        var pollCreateDTOWithInvalidStatus = "{userId: 1, status: \"INVALID\", question: \"Question\", timeLimitInMinutes: 1, privatePoll: true}";
        var pollCreateDTOWithInvalidTimeLimit = "{userId: 1, status: \"OPEN\", question: \"Question\", timeLimitInMinutes: \"invalid\", privatePoll: true}";
        var pollCreateDTOWithInvalidPrivatePollValue = "{userId: 1, status: \"OPEN\", question: \"Question\", timeLimitInMinutes: 1, privatePoll: \"invalid\"}";


        // Act
        ResultActions resultActionsInvalidUserId = mockMvc.perform(post("/poll")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(pollCreateDTOWithInvalidUserId)));

        ResultActions resultActionsInvalidStatus = mockMvc.perform(post("/poll")
                .contentType("application/json")
                .content(pollCreateDTOWithInvalidStatus));

        ResultActions resultActionsInvalidTimeLimit = mockMvc.perform(post("/poll")
                .contentType("application/json")
                .content(pollCreateDTOWithInvalidTimeLimit));

        ResultActions resultActionsInvalidPrivatePollValue = mockMvc.perform(post("/poll")
                .contentType("application/json")
                .content(pollCreateDTOWithInvalidPrivatePollValue));

        // Assert
        resultActionsInvalidUserId.andExpect(status().isBadRequest());
        resultActionsInvalidStatus.andExpect(status().isBadRequest());
        resultActionsInvalidTimeLimit.andExpect(status().isBadRequest());
        resultActionsInvalidPrivatePollValue.andExpect(status().isBadRequest());
    }

    @Test
    void getPollById_returns_not_found_when_poll_does_not_exist() throws Exception, PollNotFoundException {
        // Arrange
        doThrow(new PollNotFoundException(1L)).when(pollService).getPollById(1L);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/poll/1"));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void getPollById_returns_ok_when_poll_exists() throws Exception, PollNotFoundException {
        // Arrange
        var customer = Mockito.mock(Customer.class);
        var poll = new Poll(customer, PollStatus.OPEN, "Question", 1, true);
        poll.setId(1L);

        when(pollService.getPollById(1L)).thenReturn(poll);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/poll/1"));

        // Assert
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getPollById_returns_bad_request_when_id_is_invalid() throws Exception {
        // Arrange

        // Act
        ResultActions resultActionsString = mockMvc.perform(get("/poll/" + "invalid"));
        ResultActions resultActionsNegativeNumber = mockMvc.perform(get("/poll/-1"));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
        resultActionsNegativeNumber.andExpect(status().isBadRequest());
    }

    @Test
    void getPollByCustomerId_returns_not_found_when_customer_does_not_exist() throws Exception, CustomerNotFoundException {
        // Arrange
        doThrow(new CustomerNotFoundException(1L)).when(pollService).getPollsByUserId(1L);

        // Act
        ResultActions resultActions = mockMvc.perform(get("/poll?userId=1"));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void getPollByCustomerId_returns_bad_request_when_id_is_invalid() throws Exception {
        // Arrange

        // Act
        ResultActions resultActionsString = mockMvc.perform(get("/poll?userId=invalid"));
        ResultActions resultActionsNegativeNumber = mockMvc.perform(get("/poll?userId=-1"));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
        resultActionsNegativeNumber.andExpect(status().isBadRequest());
    }

    @Test
    void getPollByCustomerId_returns_ok_when_customer_exists_and_has_polls() throws Exception, CustomerNotFoundException {
        // Arrange
        var customer = Mockito.mock(Customer.class);
        var poll = new Poll(customer, PollStatus.OPEN, "Question", 1, true);
        poll.setId(1L);

        when(pollService.getPollsByUserId(1L)).thenReturn(java.util.List.of(poll));

        // Act
        ResultActions resultActions = mockMvc.perform(get("/poll?userId=1"));

        // Assert
        resultActions.andExpect(status().isOk());
    }

    @Test
    void getPollByCustomerId_returns_not_found_when_customer_does_not_have_polls() throws Exception, CustomerNotFoundException {
        // Arrange

        when(pollService.getPollsByUserId(1L)).thenReturn(java.util.List.of());

        // Act
        ResultActions resultActions = mockMvc.perform(get("/poll?userId=1"));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void updatePoll_returns_ok() throws PollNotFoundException, Exception {
        // Arrange
        var pollUpdateDTO = new PollUpdateDTO("Question", PollStatus.OPEN, 1, 1, 1);
        var customer = Mockito.mock(Customer.class);
        var poll = new Poll(customer, PollStatus.OPEN, "Question", 1, true);
        poll.setId(1L);

        when(pollService.updatePoll(Mockito.anyLong(), Mockito.any(PollUpdateDTO.class))).thenReturn(poll);

        // Act
        ResultActions resultActions = mockMvc.perform(put("/poll/1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(pollUpdateDTO)));

        // Assert
        resultActions.andExpect(status().isOk());
    }

    @Test
    void updatePoll_returns_not_found_when_poll_does_not_exist() throws PollNotFoundException, Exception {
        // Arrange
        var pollUpdateDTO = new PollUpdateDTO("Question", PollStatus.CLOSED, 1, 1, 1);

        doThrow(new PollNotFoundException(1L)).when(pollService).updatePoll(Mockito.anyLong(), Mockito.any(PollUpdateDTO.class));

        // Act
        ResultActions resultActions = mockMvc.perform(put("/poll/1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(pollUpdateDTO)));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void updatePoll_returns_bad_request_when_id_is_invalid() throws Exception {
        // Arrange
        var pollUpdateDTO = new PollUpdateDTO("Question", PollStatus.UNKNOWN, 1, 1, 1);

        // Act
        ResultActions resultActionsString = mockMvc.perform(put("/poll/invalid")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(pollUpdateDTO)));

        ResultActions resultActionsNegativeNumber = mockMvc.perform(put("/poll/-1")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(pollUpdateDTO)));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
        resultActionsNegativeNumber.andExpect(status().isBadRequest());
    }

    @Test
    void updatePoll_returns_bad_request_when_PollUpdateDTO_is_invalid() throws Exception {
        // Arrange
        var invalidTimeLimitString = "{question: \"Question\", timeLimitInMinutes: \"invalid\", greenCount: 1, redCount: 1}";
        var invalidTimeLimitNegative = "{question: \"Question\", timeLimitInMinutes: -10, greenCount: 1, redCount: 1}";
        var invalidGreenCountString = "{question: \"Question\", timeLimitInMinutes: 1, greenCount: \"invalid\", redCount: 1}";
        var invalidGreenCountNegative = "{question: \"Question\", timeLimitInMinutes: 1, greenCount: -10, redCount: 1}";
        var invalidRedCountString = "{question: \"Question\", timeLimitInMinutes: 1, greenCount: 1, redCount: \"invalid\"}";
        var invalidRedCountNegative = "{question: \"Question\", timeLimitInMinutes: 1, greenCount: 1, redCount: -10}";

        // Act
        for (String invalidPollUpdateDTO : new String[]{invalidTimeLimitString, invalidTimeLimitNegative, invalidGreenCountString, invalidGreenCountNegative, invalidRedCountString, invalidRedCountNegative}) {
            ResultActions resultActions = mockMvc.perform(put("/poll/1")
                    .contentType("application/json")
                    .content(invalidPollUpdateDTO));

            // Assert
            resultActions.andExpect(status().isBadRequest());
        }
    }

    @Test
    void deletePoll_returns_no_content() throws Exception, PollNotFoundException {
        // Arrange
        when(pollService.getPollById(1L)).thenReturn(Mockito.mock(Poll.class));

        // Act
        ResultActions resultActions = mockMvc.perform(delete("/poll/1"));

        // Assert
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    void deletePoll_returns_not_found_when_poll_not_found() throws Exception, PollNotFoundException {
        // Arrange
        doThrow(new PollNotFoundException(1L)).when(pollService).deletePoll(1L);

        // Act
        ResultActions resultActions = mockMvc.perform(delete("/poll/1"));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void deletePoll_returns_bad_request_when_id_is_invalid() throws Exception {
        // Arrange

        // Act
        ResultActions resultActionsString = mockMvc.perform(delete("/poll/invalid"));
        ResultActions resultActionsNegativeNumber = mockMvc.perform(delete("/poll/-1"));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
        resultActionsNegativeNumber.andExpect(status().isBadRequest());
    }

    @Test
    void deleteAllPollsByUserId_returns_no_content() throws Exception, CustomerNotFoundException {
        // Arrange
        when(pollService.getPollsByUserId(1L)).thenReturn(java.util.List.of(Mockito.mock(Poll.class)));

        // Act
        ResultActions resultActions = mockMvc.perform(delete("/poll?userId=1"));

        // Assert
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    void deleteAllPollsByUserId_returns_not_found_when_customer_not_found() throws Exception, CustomerNotFoundException {
        // Arrange
        doThrow(new CustomerNotFoundException(1L)).when(pollService).deletePollsByUserId(1L);

        // Act
        ResultActions resultActions = mockMvc.perform(delete("/poll?userId=1"));

        // Assert
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void deleteAllPollsByUserId_returns_bad_request_when_id_is_invalid() throws Exception {
        // Arrange

        // Act
        ResultActions resultActionsString = mockMvc.perform(delete("/poll?userId=invalid"));
        ResultActions resultActionsNegativeNumber = mockMvc.perform(delete("/poll?userId=-1"));

        // Assert
        resultActionsString.andExpect(status().isBadRequest());
        resultActionsNegativeNumber.andExpect(status().isBadRequest());
    }
}
