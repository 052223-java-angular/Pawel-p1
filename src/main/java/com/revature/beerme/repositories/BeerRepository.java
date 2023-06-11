package com.revature.beerme.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beerme.entities.Beer;

//for performing CRUD database operations on Beer Entity


@Repository

public interface BeerRepository extends JpaRepository<Beer, String>{
 
    Optional<Beer> findByNameIgnoreCase(String name);




}
