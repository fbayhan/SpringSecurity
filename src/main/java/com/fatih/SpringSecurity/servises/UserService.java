package com.fatih.SpringSecurity.servises;

import com.fatih.SpringSecurity.dtos.RenewPasswordDTO;
import com.fatih.SpringSecurity.dtos.UserDTO;
import com.fatih.SpringSecurity.models.Address;
import com.fatih.SpringSecurity.models.Roles;
import com.fatih.SpringSecurity.models.User;
import com.fatih.SpringSecurity.repositories.AddressRepository;
import com.fatih.SpringSecurity.repositories.RoleRepository;
import com.fatih.SpringSecurity.repositories.UserRepository;
import com.fatih.SpringSecurity.servises.email.EmailService;
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
import java.util.UUID;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

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
        user.setSurname(userDTO.getSurname());
        user.setEmailAdress(userDTO.getEmail());
        user.getRoles().add(roleAdmin);
        user.getRoles().add(roleMember);
        user.setMailConfirmed(false);
        user.setForgetPassword(false);

        UUID uuid = UUID.randomUUID();

        user.setUuidCode(uuid);

        user.setUuidExpirationDate(new Date());


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
        user.setForgetPassword(false);
        user.setPasswordUsable(false);
        userRepository.save(user);
        emailService.sendMailConfirmation();
        return user;
    }


    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);

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

    public Boolean confirmMail(String uuid) {
        User user = userRepository.findByUuidCode(UUID.fromString(uuid));
        if (user == null) {
            return false;
        } else {
            if (user.getUuidExpirationDate().after(new Date())) {
                user.setMailConfirmed(true);
                user.setPasswordUsable(true);
                userRepository.save(user);
                emailService.mailConfirmed();

                return true;
            }
        }
        return false;
    }

    public Boolean forgetPassword(String email) {
        User user = userRepository.findByEmailAdress(email);
        if (user == null) {
            return false;
        } else {
            if (user.getMailConfirmed()) {
                user.setUuidExpirationDate(new Date());
                user.setUuidCode(UUID.randomUUID());
                user.setPasswordUsable(false);
                user.setForgetPassword(true);
                emailService.sendResetPasswordMail();

                return true;
            }

        }

        return false;

    }

    public Boolean renewPassword(RenewPasswordDTO renewPasswordDTO) {

        User user = userRepository.findByUuidCode(UUID.fromString(renewPasswordDTO.getUuid()));
        if (user == null) {
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(renewPasswordDTO.getNewPassword()));
            user.setPasswordUsable(true);
            return true;
        }


    }
}
