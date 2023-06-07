package com.revature.beerme.services;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import com.revature.beerme.repositories.RoleRepository;
import com.revature.beerme.entities.Role;
import com.revature.beerme.dtos.requests.NewRoleRequest;
import com.revature.beerme.dtos.requests.NewUserRequest;
import com.revature.beerme.utils.custom_exceptions.RoleNotFoundException;


@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role saveRole(NewRoleRequest newRoleRequest) {
        Role newRole = new Role(newRoleRequest.getName());
        return roleRepository.save(newRole);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role " + name + " not found"));
    }

    public boolean isUniqueRole(String name){

        return roleRepository.findByName(name).isEmpty();
    }
    
}
