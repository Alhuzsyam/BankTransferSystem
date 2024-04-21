package com.bank.bank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Model.enitit.Nasabah;
import com.bank.bank.Model.repository.NasabahRepos;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NasabahSerice {
    @Autowired
    NasabahRepos nasabagRepos;

    public Nasabah create(Nasabah nasabah){
        return nasabagRepos.save(nasabah);
    }

    public Iterable<Nasabah> getall(){
        return nasabagRepos.findAll();
    }

    public Nasabah findOne(Long id){
        Optional<Nasabah> nasabah = nasabagRepos.findById(id);
        if(!nasabah.isPresent()){
            return null;
        }
        return nasabah.get();
    }
    public void removeNasabahById(Long id){ 
        nasabagRepos.deleteById(id);
    }
    public boolean existsByUsername(String username) {
        return nasabagRepos.existsByUsername(username);
    }

}
