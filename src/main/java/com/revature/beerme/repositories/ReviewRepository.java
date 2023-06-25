package com.revature.beerme.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beerme.entities.Review;
import com.revature.beerme.entities.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    
    Optional<Review> findById(String id);

     List<Review> findByUser(User user);

    List<Review> findByUser_Username(String username);
}







