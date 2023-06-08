package com.revature.beerme.dtos.responses;
import com.revature.beerme.entities.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Principal {

    private String id;
    private String username;
    private String role;
    private String token;


    public Principal(User user){

        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole().getName();
        
    }

    
}
