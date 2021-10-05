package com.example.demospringboot1.repository;

import com.example.demospringboot1.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepo extends CrudRepository<Province, Long> {
}
