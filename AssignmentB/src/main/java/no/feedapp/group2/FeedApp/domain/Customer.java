package no.feedapp.group2.FeedApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String email;

    private String password;

    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Collection<Poll> polls;

    protected Customer(){}

    public Customer(String userName, String email, String password, Role role){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, userName='%s', email='%s']",
                userId, userName, email);
    }
}
