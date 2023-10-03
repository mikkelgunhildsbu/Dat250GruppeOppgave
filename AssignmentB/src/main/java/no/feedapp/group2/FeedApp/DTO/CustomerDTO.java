package no.feedapp.group2.FeedApp.DTO;

import lombok.Getter;
import lombok.Setter;
import no.feedapp.group2.FeedApp.domain.Customer;

@Setter
@Getter
public class CustomerDTO {
    private Long userId;
    private String userName;
    private String email;

    public static CustomerDTO ConvertToDTO(Customer customer){
        var dto = new CustomerDTO();
        dto.setUserId(customer.getUserId());
        dto.setEmail(customer.getEmail());
        dto.setUserName(customer.getUserName());

        return dto;
    }
}
