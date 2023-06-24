package com.revature.beerme.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateProfileRequest {
    private String prp;
    private String bio;

    
}
