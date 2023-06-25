package com.revature.beerme.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class UserResponse {
    private String id;
    private String username;
   
}