package com.revature.beerme.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.beerme.dtos.requests.LoginRequest;
import com.revature.beerme.dtos.requests.NewUserRequest;
import com.revature.beerme.dtos.requests.PBRequest;
import com.revature.beerme.dtos.requests.UpdateProfileRequest;
import com.revature.beerme.entities.User;
import com.revature.beerme.repositories.UserRepository;
import com.revature.beerme.services.RoleService;
import com.revature.beerme.utils.custom_exceptions.UserNotFoundException;
import com.revature.beerme.entities.Role;
import com.revature.beerme.dtos.responses.Principal;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    

    public User registerUser(NewUserRequest newUserRequest){

        if (!isUniqueUsername(newUserRequest.getUsername())) {
        throw new RuntimeException("Username already exists"); // You can create a custom exception for this if you want
    }
        //find role User
        Role userRole = roleService.findByName("USER");

        //hash password
        String hashed = BCrypt.hashpw(newUserRequest.getPassword(), BCrypt.gensalt());

        //create new user
        User newUser = new User(newUserRequest.getUsername(), hashed, userRole);

        //save new user and return to controller
        return userRepository.save(newUser);
    }



    public boolean isValidUsername(String username) {
        // check if username is valid
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");

    }
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.isEmpty();
    }
    public boolean isValidPassword(String password) {
        // check if password is valid
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$");
    }

    public boolean isSamePassword(String password, String confirmedPassword) {
        // check if password and confirmed password match
        return password.equals(confirmedPassword);
    }

    public Principal login(LoginRequest loginReq){
        Optional<User> userOpt = userRepository.findByUsername(loginReq.getUsername());

        if(userOpt.isPresent()){
            User foundUser = userOpt.get();
            if(BCrypt.checkpw(loginReq.getPassword(), foundUser.getPassword())){
                return new Principal(foundUser);
            }

        }

            throw new UserNotFoundException("Invalid Credentials");
    
    }


    public User findByUserName(String username) {
        logger.info("Finding user by username: " + username);
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isPresent()) {
            logger.info("User found: " + userOpt.get());
            return userOpt.get();
        }
        logger.warn("User not found");
        throw new UserNotFoundException("User not found");
    }

   public User updateUser(String id, PBRequest pbreq) {
    Optional<User> userOpt = userRepository.findById(id);
    if(userOpt.isPresent()) {
        User user = userOpt.get();
        user.setPrp(pbreq.getPrp());
        user.setBio(pbreq.getBio());
        userRepository.save(user);
        return user;
    }
    throw new UserNotFoundException("User not found");
}

public User findByUserId(String id){
    Optional<User> userOpt = userRepository.findById(id);
    if(userOpt.isPresent()) {
        return userOpt.get();
    }
    throw new UserNotFoundException("User not found");
}
  public User updateUserProfile(String username, UpdateProfileRequest updateProfileRequest) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPrp(updateProfileRequest.getPrp());
            user.setBio(updateProfileRequest.getBio());
            userRepository.save(user);
            return user;
        }
        throw new UserNotFoundException("User not found");
    }

}
