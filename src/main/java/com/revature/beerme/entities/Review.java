package com.revature.beerme.entities;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    private String id;

    @Column(name = "rating", nullable = false)
    private String rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    @OneToMany(mappedBy = "user")
    private Set<User> user;

    @ManyToOne
    private Set<Beer> beer;

    @OneToMany(mappedBy = "brewery")
    private Set<Brewery> brewery;
    
    
}
