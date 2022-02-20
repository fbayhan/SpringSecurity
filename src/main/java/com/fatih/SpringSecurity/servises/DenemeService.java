package com.fatih.SpringSecurity.servises;


import com.fatih.SpringSecurity.models.Deneme;
import com.fatih.SpringSecurity.repositories.DenemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class DenemeService {

    @Autowired
    DenemeRepository denemeRepository;


    public Deneme deneme(){


        Deneme deneme=new Deneme();
        deneme.setName("Fatih");
        deneme.setSurname("Bayhan");
        denemeRepository.save(deneme);
        return deneme;
    }

}
