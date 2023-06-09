package com.revature.beerme.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beerme.dtos.requests.LoginRequest;
import com.revature.beerme.dtos.requests.NewUserRequest;
import com.revature.beerme.dtos.requests.ReviewRequest;
import com.revature.beerme.dtos.responses.Principal;
import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.Review;
import com.revature.beerme.entities.User;
import com.revature.beerme.services.BeerService;
import com.revature.beerme.services.JwtTokenService;
import com.revature.beerme.services.UserService;
import com.revature.beerme.utils.custom_exceptions.ResourceConflictException;
import lombok.AllArgsConstructor;
import com.revature.beerme.services.ReviewService;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenService tokenService;
    private final BeerService beerService;
    private final ReviewService reviewService;

    


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

//-----HelperMethods-----------------------------------------------------------------------------------------------------------------------------//




    
}
