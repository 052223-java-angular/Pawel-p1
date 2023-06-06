package com.revature.beerme.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column
    private String password;

    //One user can have many reviews
    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;






    
}
