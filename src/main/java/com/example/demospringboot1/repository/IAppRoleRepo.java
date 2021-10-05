package com.example.demospringboot1.repository;

import com.example.demospringboot1.model.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepo extends CrudRepository<AppRole, Long> {
}
