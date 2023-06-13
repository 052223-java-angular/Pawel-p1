package com.revature.beerme.dtos.requests;

import com.revature.beerme.entities.Beer;
import com.revature.beerme.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FavoriteRequest {

    String username;
    String beername;
    
}
