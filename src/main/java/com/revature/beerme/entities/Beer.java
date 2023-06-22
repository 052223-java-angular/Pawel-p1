package com.revature.beerme.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "beers")
public class Beer {

    @Id
    private String id;

    private String name;

    private String style;

    private String abv;

    @ManyToOne
    @JoinColumn(name = "brewery_id", nullable = false)
    @JsonBackReference
    private Brewery brewery;

    @OneToMany(mappedBy = "beer", fetch = FetchType.EAGER)
    @JsonManagedReference(value="beer-review")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "beer", fetch = FetchType.EAGER)
    @JsonManagedReference(value="beer-favorite")
    private Set<Favorite> favorites;


    
}
