package com.revature.beerme.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class ReviewRequest {

    private String username;
    private String beername;
    private String breweryname;
    private String comment;
    private String rating;

    
}
