package no.feedapp.group2.FeedApp.domain;

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
    private Collection<Poll> polls;

    protected Customer(){}

    public Customer(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, userName='%s', email='%s']",
                userId, userName, email);
    }
}
