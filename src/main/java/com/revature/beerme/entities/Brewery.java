package com.revature.beerme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "breweries")
public class Brewery {
    
    @Id
    private String id;

    private String name;

    private String location;

    private String averageRating;
}
