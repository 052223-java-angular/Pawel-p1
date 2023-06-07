package com.revature.beerme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.revature.beerme.entities.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, String>{


    Optional<Role> findByName(String roleName);
    
}
