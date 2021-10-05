package com.example.demospringboot1.service.provinceservice;

import com.example.demospringboot1.model.Province;

import java.util.List;
import java.util.Optional;

public interface IServiceProvince {
    List<Province> showAll();
    void save(Province province);
    void delete(long id);
    Optional<Province> findById(long id);
}
