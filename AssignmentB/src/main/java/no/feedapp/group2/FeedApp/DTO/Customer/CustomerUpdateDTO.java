package no.feedapp.group2.FeedApp.DTO.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDTO {
    private String userName;
    private String email;
    private String password;
}
