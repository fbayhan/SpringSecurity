package com.fatih.SpringSecurity.controllers;


import com.fatih.SpringSecurity.models.redis.Cities;
import com.fatih.SpringSecurity.servises.redis.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CitiesController {

    @Autowired
    CitiesService citiesService;

    @GetMapping
    public ResponseEntity<List<Cities>> getAllCities(){
        return ResponseEntity.ok(citiesService.getAllCities());
    }

    @PostMapping
    public  ResponseEntity<Cities> createNewCity(@RequestBody Cities city){
        return ResponseEntity.ok(citiesService.newCity(city));

    }

}
