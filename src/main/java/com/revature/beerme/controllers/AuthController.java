package com.revature.beerme.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beerme.dtos.requests.FavoriteRequest;
import com.revature.beerme.dtos.requests.LoginRequest;
import com.revature.beerme.dtos.requests.NewUserRequest;
import com.revature.beerme.dtos.requests.PBRequest;
import com.revature.beerme.dtos.requests.ReviewRequest;
import com.revature.beerme.dtos.responses.BeerReview;
import com.revature.beerme.dtos.responses.Principal;
import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.Favorite;
import com.revature.beerme.entities.Review;
import com.revature.beerme.entities.User;
import com.revature.beerme.services.BeerService;
import com.revature.beerme.services.FavoriteService;
import com.revature.beerme.services.JwtTokenService;
import com.revature.beerme.services.UserService;
import com.revature.beerme.utils.custom_exceptions.ResourceConflictException;
import lombok.AllArgsConstructor;
import com.revature.beerme.services.ReviewService;
import com.revature.beerme.services.FavoriteService;


@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenService tokenService;
    private final BeerService beerService;
    private final ReviewService reviewService;
    private final FavoriteService favoriteService;

    


    @PostMapping("/register")
        //deserialize JSON from request body 
        //take in NewUserRequest object as parameter
        public ResponseEntity<?> registerUser(@RequestBody NewUserRequest newUserRequest) {
            //validate user registration information via user service
            if(!userService.isValidUsername(newUserRequest.getUsername())) {
                throw new ResourceConflictException(
                    "Username is must be 8-20 characters long and can only contain letters, numbers, periods and underscores");
            }
            //check unique username
            if (!userService.isUniqueUsername(newUserRequest.getUsername())) {
                throw new ResourceConflictException("Username is not unique");
            }

            //check password validity
            if (!userService.isValidPassword(newUserRequest.getPassword())) {
                throw new ResourceConflictException(
                        "Password needs to be at least 8 characters long and contain at least one letter and one number");
            }
            
            //check password and confirmed password match
            if (!userService.isSamePassword(newUserRequest.getPassword(), newUserRequest.getConfirmedPassword())) {
                throw new ResourceConflictException("Passwords do not match");
            }

            
            // call user service method to register user
            userService.registerUser(newUserRequest);

            // return response entity (201 created)
           return ResponseEntity.status(HttpStatus.CREATED).build();
        }



        @PostMapping("/login")

        public ResponseEntity<Principal> login(@RequestBody LoginRequest loginReq) {
            //userservice to call login method

            Principal principal = userService.login(loginReq);

            //create jwt token

            String token = tokenService.generateToken(principal);

            principal.setToken(token);



            // return status ok and return principal object

            return ResponseEntity.status(HttpStatus.OK).body(principal);
 
        }

        @PostMapping("/review")

        public ResponseEntity<?> addReview(@RequestBody ReviewRequest req){

            //get User object from db by username
            User user = userService.findByUserName(req.getUsername());

            //get beer object from db by beername

            Beer beer = beerService.findByBeerName(req.getBeername());

            //save review object to db

            reviewService.createReview(req, user, beer);

            //return response entity (201 created)

            return ResponseEntity.status(HttpStatus.CREATED).build();


            
        }

        @GetMapping("/reviews")
        public ResponseEntity<List<BeerReview>> getReviews(){
            
            List<BeerReview> beerReviews = reviewService.getAllReviews();

            if(!beerReviews.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(beerReviews);
            }else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }

        @GetMapping("/reviews/{beername}")

            public ResponseEntity<List<BeerReview>> getBeerReviews(@PathVariable String beername){

               List<BeerReview> beerReviews = reviewService.getBeerReviewsByBeerName(beername);

               if(!beerReviews.isEmpty()){
                    return ResponseEntity.status(HttpStatus.OK).body(beerReviews);
                } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            }
    
        @PutMapping("/reviews/{id}")

        public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody ReviewRequest req){

            //retrieve review object corresponding to the id sent to endpoint
            Review review = reviewService.updateReviewById(id, req);

            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        @GetMapping("/search")
        public ResponseEntity<List<Beer>> searchBeer(@RequestParam("name") String name){
        // Get list of beers that match search criteria from beerService
        List<Beer> beers = beerService.searchBeersByName(name);
        
        // Check if we found any beers
        if (!beers.isEmpty()) {
            // If beers found, return the beers with status 200 OK
            return ResponseEntity.status(HttpStatus.OK).body(beers);
        } else {
            // If no beers found, return status 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
        @PostMapping("/favorite")
        public ResponseEntity<?> addFave(@RequestBody FavoriteRequest freq){

            Beer beer = beerService.findByBeerName(freq.getBeername());

            User user = userService.findByUserName(freq.getUsername());

            favoriteService.createFavorite(user, beer);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        @PostMapping("/profile/{id}")

            public ResponseEntity<User> addProfile(@PathVariable String id, @RequestBody PBRequest pbreq){

                User newUser = userService.findByUserId(id, pbreq);

                return ResponseEntity.status(HttpStatus.OK).body(newUser);

            }

    @GetMapping("/beers")
        public ResponseEntity<List<Beer>> getAllBeers(){
        //get list of beers from beerService
        List<Beer> beers = beerService.getAllBeers();
        
        //check if beers found
        if (!beers.isEmpty()) {
            // if beers found, return the beers with status 200 OK
            return ResponseEntity.status(HttpStatus.OK).body(beers);
        } else {
            //if no beers found, return status 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
}


        // @PutMapping("/profile/{id}")

        // public ResponseEntity<User> updateProfile(@PathVariable String id, @RequestBody PBRequest pbreq){


        //         User updateUser = userService.findByUserId(id, pbreq);

                

        // }
            


        }
    




//-----HelperMethods-----------------------------------------------------------------------------------------------------------------------------//




    

