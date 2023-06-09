package com.revature.beerme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beerme.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    
}
