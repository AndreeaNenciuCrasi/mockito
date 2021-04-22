package com.example.mockito.controller;

import com.example.mockito.model.Response;
import com.example.mockito.model.User;
import com.example.mockito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/addUser")
    public Response save(@RequestBody User user){
        return service.saveUser(user);
    }

    @GetMapping("/getUsers")
    public Response getAllUsers(){
        return service.getAllUsers();
    }
}
