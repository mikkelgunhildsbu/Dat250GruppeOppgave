package no.feedapp.group2.FeedApp;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String email;

    private String password;

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
                "Customer[id=%d, email='%s', password='%s']",
                userId, email, password);
    }
}
