package com.revature.beerme.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.beerme.dtos.requests.NewUserRequest;
import com.revature.beerme.services.UserService;



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
                return ResponseEntity.status(400).body("Username is already taken");
            }
            
            
            // call service method to register user


            // return response entity
            return null;
        }



    @PostMapping("/login")
    
}
