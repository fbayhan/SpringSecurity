package com.fatih.SpringSecurity.servises;

import com.fatih.SpringSecurity.models.Roles;
import com.fatih.SpringSecurity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Roles newRole() {
        Roles roleAdmin = new Roles();
        roleAdmin.setRoleName("ADMIN");
        roleRepository.save(roleAdmin);
        Roles roleUser = new Roles();
        roleUser.setRoleName("USER");
        roleRepository.save(roleUser);
        return roleUser;
    }

}
