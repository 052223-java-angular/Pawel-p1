package com.revature.beerme.services;

import java.util.Optional;

import mindrot.jbcrypt.BCrypt;
import com.revature.beerme.dtos.requests.NewUserRequest;
import com.revature.beerme.entities.User;
import com.revature.beerme.repositories.UserRepository;






public class UserService {



    public boolean isValidUsername(String username) {
        // check if username is valid
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");

    }
    public boolean isPasswordValid(String password) {
        // check if password is valid
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]");
    }

    public boolean isPasswordConfirmed(String password, String confirmedPassword) {
        // check if password and confirmed password match
        return password.equals(confirmedPassword);
    }



    
}
