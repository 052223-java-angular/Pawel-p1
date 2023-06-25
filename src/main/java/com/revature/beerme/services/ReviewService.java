package com.revature.beerme.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.beerme.dtos.requests.ReviewRequest;
import com.revature.beerme.dtos.responses.BeerReview;
import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.Review;
import com.revature.beerme.entities.User;
import com.revature.beerme.repositories.ReviewRepository;

import lombok.AllArgsConstructor;




@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BeerService beerService;

    public void createReview(ReviewRequest reviewRequest, User user, Beer beer) {
        Review review = new Review();
        review.setId(UUID.randomUUID().toString());
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        review.setBeer(beer);
        review.setUser(user);
        reviewRepository.save(review);
    }

    public List<BeerReview> getAllReviews() {
    List<Review> reviews = reviewRepository.findAll();
    List<BeerReview> beerReviews = new ArrayList<>();

    for(Review review : reviews) {
        BeerReview beerReview = new BeerReview(review, review.getBeer());
        beerReviews.add(beerReview);

    }
    return beerReviews;
    }

    public List<BeerReview> getBeerReviewsByBeerName(String beername){

        //initialize beerReview List
        List<BeerReview> beerReviews = new ArrayList<>();

        //retrieve beer object with matching beername
        Beer beer = beerService.findByBeerName(beername);

        //retrieve a list of reviews with matching beer_id from beer object

        Set<Review> reviewSet = beer.getReviews();

        for(Review review : reviewSet){

            BeerReview beerReview = new BeerReview(review, beer);
            beerReviews.add(beerReview);
        }
        
        return beerReviews;
    }

     public Review updateReviewById(String id, ReviewRequest req){

        Optional<Review> existingReview = reviewRepository.findById(id);

        if(existingReview.isPresent()){

            Review review = existingReview.get();

            review.setComment(req.getComment());
            review.setRating(req.getComment());

            reviewRepository.save(review);

            return review;


        }

        throw new NoSuchElementException("Review with id " + id + " not found.");

     }

     public List<BeerReview> getUserReviews(String username) {
    // retrieve the reviews of a specific user from the repository
    List<Review> reviews = reviewRepository.findByUser_Username(username);
    
    // Convert the reviews to DTOs
    List<BeerReview> beerReviews = new ArrayList<>();
    for (Review review : reviews) {
        beerReviews.add(new BeerReview(review, review.getBeer()));
    }

    // Return the DTOs
    return beerReviews;
}


   

//----------Helper Methods-------///




}
