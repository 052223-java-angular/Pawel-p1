package com.revature.beerme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.Favorite;
import com.revature.beerme.entities.User;
import com.revature.beerme.repositories.FavoriteRepository;

import java.util.UUID;



import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor


public class FavoriteService {

    private final FavoriteRepository faveRepo;

         public void createFavorite(User user, Beer beer){

            String faveID = UUID.randomUUID().toString();

            Favorite fave = new Favorite(faveID, user, beer);

            faveRepo.save(fave);
        }

    public List<Favorite> getFavoritesByUser(User user) {
        return faveRepo.findByUser(user);
}
}
