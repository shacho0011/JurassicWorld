package com.mohit.jmc.service;

import com.mohit.jmc.dto.UserRegDto;
import com.mohit.jmc.model.User;

public interface UserService {

    User getUserByUsername(String username);
    User createOrUpdateUser(User user, UserRegDto userRegDto);

}
