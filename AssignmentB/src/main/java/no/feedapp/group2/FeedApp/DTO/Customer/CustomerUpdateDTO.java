package no.feedapp.group2.FeedApp.DTO.Customer;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDTO {
    private String userName;

    @Email
    private String email;

    private String password;

    public CustomerUpdateDTO(String name, String mail, String test) {
        this.userName = name;
        this.email = mail;
        this.password = test;
    }
}
