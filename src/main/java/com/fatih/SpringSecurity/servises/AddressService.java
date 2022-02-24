package com.fatih.SpringSecurity.servises;

import com.fatih.SpringSecurity.models.Address;
import com.fatih.SpringSecurity.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address getById(Long addressId) {
        Address address = addressRepository.getById(addressId);
        return address;
    }
}
