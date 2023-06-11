package com.revature.beerme.dtos.responses;
import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.Review;
import com.revature.beerme.entities.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BeerReview {

    private String id;
    private String comment;
    private String rating;
    private String user;
    private String beer;



    public BeerReview(Review review, Beer beer){

        this.id = review.getId();
        this.comment = review.getComment();
        this.rating = review.getRating();
        this.user = review.getUser().getUsername();
        this.beer = beer.getName();
        
    }

    
}
