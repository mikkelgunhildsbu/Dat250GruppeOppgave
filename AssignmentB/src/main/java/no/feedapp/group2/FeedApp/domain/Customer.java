package no.feedapp.group2.FeedApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
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
