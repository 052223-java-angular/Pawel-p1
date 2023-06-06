package com.revature.beerme.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "breweries")
public class Brewery {
    
    @Id
    private String id;

    private String name;

    private String location;

    private String averageRating;

    @OneToMany(mappedBy = "brewery", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Beer> beers;


}
