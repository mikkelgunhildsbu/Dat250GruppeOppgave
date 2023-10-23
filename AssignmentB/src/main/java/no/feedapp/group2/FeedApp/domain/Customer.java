package no.feedapp.group2.FeedApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank
    private String userName;

    @Email
    private String email;

    @NotBlank
    private String password;

    @Valid
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Collection<Poll> polls;

    protected Customer() {
    }

    public Customer(String userName, String email, String password, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
