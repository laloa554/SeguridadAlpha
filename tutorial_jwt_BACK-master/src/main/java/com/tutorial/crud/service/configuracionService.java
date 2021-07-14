package com.tutorial.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.entity.configuracion;
import com.tutorial.crud.repository.configuracionRepository;

@Service
@Transactional
public class configuracionService {
	@Autowired
    configuracionRepository configuracionRepository;
	
    public List<configuracion> list(){
        return configuracionRepository.findAll();
    }

    public Optional<configuracion> getOne(int id){
        return configuracionRepository.findById(id);
    }

    public Optional<configuracion> findByServiceName(String service_name){
        return configuracionRepository.findByServiceName(service_name);
    }

    public void  save(configuracion configuracion){
    	configuracionRepository.save(configuracion);
    }

    public void delete(int id){
    	configuracionRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return configuracionRepository.existsById(id);
    }

    public boolean existsByServiceName(String service_name){
        return configuracionRepository.existsByServiceName(service_name);
    }

}
