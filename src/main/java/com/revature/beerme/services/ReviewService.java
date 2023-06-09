package com.revature.beerme.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.revature.beerme.dtos.requests.ReviewRequest;
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

}