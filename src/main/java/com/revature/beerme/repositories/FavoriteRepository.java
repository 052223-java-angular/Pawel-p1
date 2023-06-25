package com.revature.beerme.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beerme.entities.Favorite;
import com.revature.beerme.entities.User;

//for performing CRUD database operations on Beer Entity


@Repository

public interface FavoriteRepository extends JpaRepository<Favorite, String>{
 
       List<Favorite> findByUser(User user);

}
