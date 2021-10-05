package com.example.demospringboot1.repository;

import com.example.demospringboot1.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepo extends CrudRepository<AppUser,Long> {
    AppUser findByUserName(String name);
}
