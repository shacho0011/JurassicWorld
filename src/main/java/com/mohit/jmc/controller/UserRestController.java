package com.mohit.jmc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohit.jmc.dto.UserOverviewDto;
import com.mohit.jmc.model.User;
import com.mohit.jmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    ResponseEntity<Object> getUserByUsername(@RequestBody String requestData){
        ResponseEntity<Object> responseEntity = null;

        try {
            UserOverviewDto userOverviewDto = new ObjectMapper().readValue(requestData, UserOverviewDto.class);
            User user = null;
            if(userOverviewDto.getEmail() != null && userOverviewDto.getPassword() != null){
                user = userService.getUserByUsername(userOverviewDto.getEmail());
                if(user != null){
                    if(passwordEncoder.matches(userOverviewDto.getPassword(), user.getPassword())){
                        responseEntity = new ResponseEntity<>("Success", null, HttpStatus.OK);
                    }else {
                        responseEntity = new ResponseEntity<>("Password mismatch!", null, HttpStatus.NOT_FOUND);
                    }
                }else{
                    responseEntity = new ResponseEntity<>("User not found!", null, HttpStatus.NOT_FOUND);
                }

            }else {
                responseEntity = new ResponseEntity<>("Request error", null, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            responseEntity = new ResponseEntity<>("Internal server error!", null, HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }


        return responseEntity;
    }
}
