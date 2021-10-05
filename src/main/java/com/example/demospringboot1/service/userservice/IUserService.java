package com.example.demospringboot1.service.userservice;

import com.example.demospringboot1.model.AppUser;

public interface IUserService {
    AppUser getUserByUsername(String name);
}
