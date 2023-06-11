package com.revature.beerme.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beerme.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    
    Optional<Review> findById(String id);



}
