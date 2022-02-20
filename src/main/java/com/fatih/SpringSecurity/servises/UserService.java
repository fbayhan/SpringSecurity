package com.fatih.SpringSecurity.servises;

import com.fatih.SpringSecurity.dtos.UserDTO;
import com.fatih.SpringSecurity.models.Address;
import com.fatih.SpringSecurity.models.Roles;
import com.fatih.SpringSecurity.models.User;
import com.fatih.SpringSecurity.repositories.AddressRepository;
import com.fatih.SpringSecurity.repositories.RoleRepository;
import com.fatih.SpringSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    AddressRepository addressRepository;

    public User addNewUser(UserDTO userDTO) {

        Roles roleAdmin = roleRepository.getById(1L);
        Roles roleMember = roleRepository.getById(2L);


        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setActive(true);
        user.setName(userDTO.getName());
        user.setCreatedDate(new Date());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.getRoles().add(roleAdmin);
        user.getRoles().add(roleMember);

        Address address = new Address();
        address.setAddress("mahalle sokak cadde");
        address.setActive(true);
        address.setCity("KAYSERİ");
        address.setCountry("TURKEY");
        address.setState("Melikgazi");
        address.setZipCode("38051");
        address.setCreatedDate(new Date());

        addressRepository.save(address);
        address.setUser(user);


        user.setAddress(address);

        userRepository.save(user);

        return user;
    }


    public User getUserByUserName(String userName){
        User user=userRepository.findByUserName(userName);

        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Some of the user information is wrong " + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}
