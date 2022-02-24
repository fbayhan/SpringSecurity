package com.fatih.SpringSecurity.controllers;

import com.fatih.SpringSecurity.dtos.UserDTO;
import com.fatih.SpringSecurity.models.Address;
import com.fatih.SpringSecurity.models.User;
import com.fatih.SpringSecurity.servises.AddressService;
import com.fatih.SpringSecurity.servises.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/test")
    public String someThing() {
        return "no need to auth";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.addNewUser(userDTO));
    }

    @GetMapping(value = "jsontest")
    public User entityJsonProblemTest() {
        User user = userService.getUserByUserName("rarar");
        return user;
    }

    @GetMapping(value = "jsontestchild")
    public Address entityJsonProblemTestAddress() {
        Address address = addressService.getById(5L);

        return address;
    }


}
