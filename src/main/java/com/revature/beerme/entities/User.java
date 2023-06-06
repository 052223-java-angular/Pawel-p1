package com.revature.beerme.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    //Many userscan have one role
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Role role;







    
}
