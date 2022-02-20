package com.fatih.SpringSecurity.servises.redis;


import com.fatih.SpringSecurity.models.redis.Cities;
import com.fatih.SpringSecurity.repositories.redis.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CitiesService {

    @Autowired
    CitiesRepository citiesRepository;

    public List<Cities> getAllCities() {
        return (List<Cities>) citiesRepository.findAll();
    }

    public Cities getCitiesById(Long id) {
        return citiesRepository.findById(id).orElseThrow(() -> new RuntimeException("bu iddeki şehir bulunamadı:" + id));
    }

    public  Cities newCity(Cities city){
        return citiesRepository.save(city);

    }


}
