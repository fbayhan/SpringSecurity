package com.fatih.SpringSecurity.controllers;

import com.fatih.SpringSecurity.dtos.UserDTO;
import com.fatih.SpringSecurity.models.Deneme;
import com.fatih.SpringSecurity.servises.DenemeService;
import com.fatih.SpringSecurity.servises.RoleService;
import com.fatih.SpringSecurity.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class GetController {
    @Autowired
    DenemeService denemeService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/rename")
    public String deneme() {
        System.out.println("Deneme çalışıyor");
        Deneme deneme = denemeService.deneme();
        System.out.println(deneme.toString());
        return "fatih";
    }


    @GetMapping(value = "/addrole")
    public String addRole() {
        roleService.newRole();
        return "Admin ve User Rolleri eklendi";
    }

    @GetMapping(value = "/addmember")
    public String addMember()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("aaa@b.com");
        userDTO.setName("aaa");
        userDTO.setPassword("123456");
        userDTO.setUserName("aab");
        userService.addNewUser(userDTO);
        return "üye eklendi";
    }
}
