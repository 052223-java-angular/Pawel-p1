package com.revature.beerme.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beerme.entities.User;

//for performing database operations on User Entity


@Repository
public interface UserRepository extends JpaRepository<User, String>{
 
    Optional<User> findByUsername(String username);




}
