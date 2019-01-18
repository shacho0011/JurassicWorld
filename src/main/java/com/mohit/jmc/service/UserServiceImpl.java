package com.mohit.jmc.service;

import com.mohit.jmc.model.User;
import com.mohit.jmc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByUsername(String username){
        User user = null;

        try {
            user = userRepository.findByEmail(username);
        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }
}
