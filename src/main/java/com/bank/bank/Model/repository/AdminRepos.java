package com.bank.bank.Model.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.bank.Model.enitit.Admin;

public interface AdminRepos extends CrudRepository <Admin, Long>  {
    boolean existsByUsername(String username);
}
