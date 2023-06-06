package com.revature.beerme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    private String id;

    private String name;

    private String style;

    private String abv;

    @ManytoOne
    private String breweryId;
    
}
