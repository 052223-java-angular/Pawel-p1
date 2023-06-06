package com.revature.beerme.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
}
