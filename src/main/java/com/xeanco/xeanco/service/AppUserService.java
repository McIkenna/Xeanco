package com.xeanco.xeanco.service;

import com.xeanco.xeanco.IService.IUserService;
import com.xeanco.xeanco.exception.UsernameExistException;
import com.xeanco.xeanco.model.AppUser;
import com.xeanco.xeanco.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class AppUserService implements IUserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    public AppUser saveUser(AppUser user) {

        try{
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setUsername(user.getUsername());
                user.setConfirmPassword("");
                return userRepository.save(user);


        }catch(Exception ex){
            throw new UsernameExistException("Username " + user.getUsername() + " already exist");
        }
        //Username has to be unique
        //passwords match
        //dont persist confirm password

    }




    public AppUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(String username){
        AppUser appUser = userRepository.findByUsername((username));
        userRepository.delete(appUser);
    }


    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
