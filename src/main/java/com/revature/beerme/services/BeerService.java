package com.revature.beerme.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.Review;
import com.revature.beerme.entities.User;
import com.revature.beerme.repositories.BeerRepository;
import com.revature.beerme.repositories.ReviewRepository;
import com.revature.beerme.repositories.UserRepository;
import com.revature.beerme.utils.custom_exceptions.UserNotFoundException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

     public Beer findByBeerName(String beername) {
        Optional<Beer> beerOpt = beerRepository.findByNameIgnoreCase(beername);
        if(beerOpt.isPresent()) {
            return beerOpt.get();
        }
        throw new UserNotFoundException("Beer not found");
    }

    public Beer findByBeerId(String beerId) {
        Optional<Beer> beerOpt = beerRepository.findById(beerId);
        if(beerOpt.isPresent()) {
            return beerOpt.get();
        }
        throw new UserNotFoundException("Beer not found");
    }

    public List<Beer> searchBeersByName(String name) {
        return beerRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
}
    public Optional<Beer> getBeerById(String id){
    //fetch beer from database using id
    return beerRepository.findById(id);
} 
    public List<Beer> getUserReviewedBeers(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            List<Review> userReviews = reviewRepository.findByUser(user);
            return userReviews.stream().map(Review::getBeer).collect(Collectors.toList());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

 
}
