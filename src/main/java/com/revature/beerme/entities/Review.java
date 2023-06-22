package com.revature.beerme.entities;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "rating", nullable = false)
    private String rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    //Each review is associated with one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private User user;

    //Each review is associated with one beer
    @ManyToOne
    @JoinColumn(name = "beer_id", nullable = false) 
    @JsonBackReference(value="beer-review")
    private Beer beer;
     
    
}
