package com.revature.beerme.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * NewUserRequest class mapped to json request body
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewUserRequest {

    private String username;
    private String password;
    private String confirmedPassword;
    
}
