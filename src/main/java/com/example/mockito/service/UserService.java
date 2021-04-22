package com.example.mockito.service;

import com.example.mockito.dao.UserRepository;
import com.example.mockito.model.Response;
import com.example.mockito.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Response saveUser(User user){
        repository.save(user);
        return new Response("saved user with id" + user.getId(), Boolean.TRUE);
    }

    public Response getAllUsers(){
        List<User> users = repository.findAll();
        return new Response("number of Users: " + users.size(), Boolean.TRUE);
    }
}
