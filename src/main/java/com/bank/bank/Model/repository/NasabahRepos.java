package com.bank.bank.Model.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.bank.Model.enitit.Nasabah;

public interface NasabahRepos extends CrudRepository <Nasabah, Long> {
    boolean existsByUsername(String username);
}
