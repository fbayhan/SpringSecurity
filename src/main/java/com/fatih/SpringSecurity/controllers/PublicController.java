package com.fatih.SpringSecurity.controllers;

import com.fatih.SpringSecurity.dtos.EmailDTO;
import com.fatih.SpringSecurity.dtos.RenewPasswordDTO;
import com.fatih.SpringSecurity.dtos.UserDTO;
import com.fatih.SpringSecurity.models.Address;
import com.fatih.SpringSecurity.models.User;
import com.fatih.SpringSecurity.servises.AddressService;
import com.fatih.SpringSecurity.servises.UserService;
import com.fatih.SpringSecurity.servises.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    Logger logger = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    EmailService emailService;


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
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        Address address = addressService.getById(5L);
        return address;
    }

    @GetMapping(value = "confirmmail/{uuid}")
    public ResponseEntity<String> confirmEmail(@PathVariable String uuid) {
        Boolean isUuidActive = userService.confirmMail(uuid);
        if (isUuidActive) {
            return new ResponseEntity<>("Hesap aktif edildi", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("UUID süresi dolmuş, yenileyin", HttpStatus.UNAUTHORIZED);
        }


    }

    @PostMapping(value = "forgetpassword")
    public ResponseEntity<String> forgetPassword(@RequestBody EmailDTO emailDTO) {
        Boolean passwordForgetable = userService.forgetPassword(emailDTO.getMail());

        if (passwordForgetable) {
            return new ResponseEntity<>("Reset password mail sent", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("There is no such a email, please register or check email address", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "renewpassword")
    public ResponseEntity<String> renewPassword(@RequestBody RenewPasswordDTO renewPasswordDTO) {
        Boolean renew = userService.renewPassword(renewPasswordDTO);

        if (renew) {
            return new ResponseEntity<>("Your password changed", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("There is no such a email, please register or check email address", HttpStatus.UNAUTHORIZED);

        }
    }


}
