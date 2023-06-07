package com.revature.beerme.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.beerme.dtos.requests.NewUserRequest;
import com.revature.beerme.services.UserService;
import com.revature.beerme.utils.custom_exceptions.ResourceConflictException;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    


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



    //@PostMapping("/login")
    
}
