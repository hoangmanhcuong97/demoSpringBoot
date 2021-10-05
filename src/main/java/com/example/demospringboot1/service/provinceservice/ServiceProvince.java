package com.example.demospringboot1.service.provinceservice;

import com.example.demospringboot1.model.Province;
import com.example.demospringboot1.repository.IProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceProvince implements IServiceProvince{
    @Autowired
    private IProvinceRepo provinceRepo;
    @Override
    public List<Province> showAll() {
        return (List<Province>) provinceRepo.findAll();
    }

    @Override
    public void save(Province province) {
        provinceRepo.save(province);
    }

    @Override
    public void delete(long id) {
        provinceRepo.deleteById(id);
    }

    @Override
    public Optional<Province> findById(long id) {
        return provinceRepo.findById(id);
    }
}
