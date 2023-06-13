package com.revature.beerme.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Review> reviews;

    //Many users can have one role
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Favorite> favorites;

    private String prp;

    private String bio;

    public User(String username, String password, Role role){
        this.id = java.util.UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.reviews = new HashSet<>();
        this.role = role;
        
    }

}
