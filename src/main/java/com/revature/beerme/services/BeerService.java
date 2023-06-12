package com.revature.beerme.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.beerme.entities.Beer;
import com.revature.beerme.repositories.BeerRepository;
import com.revature.beerme.utils.custom_exceptions.UserNotFoundException;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;


     public Beer findByBeerName(String beername) {
        Optional<Beer> beerOpt = beerRepository.findByNameIgnoreCase(beername);
        if(beerOpt.isPresent()) {
            return beerOpt.get();
        }
        throw new UserNotFoundException("Beer not found");
    }

    public Beer findByBeerId(String beerId) {
        Optional<Beer> beerOpt = beerRepository.findById(beerId);
        if(beerOpt.isPresent()) {
            return beerOpt.get();
        }
        throw new UserNotFoundException("Beer not found");
    }

    public List<Beer> searchBeersByName(String name) {
        return beerRepository.findByNameContainingIgnoreCase(name);
    }



    
}
