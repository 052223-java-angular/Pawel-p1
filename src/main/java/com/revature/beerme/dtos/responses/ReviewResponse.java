package com.revature.beerme.dtos.responses;

import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.Review;
import com.revature.beerme.entities.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;




public class ReviewResponse {
    
    private String id;
    private String rating;
    private String comment;
    private UserResponse user;
    private BeerResponse beer;
    
}
