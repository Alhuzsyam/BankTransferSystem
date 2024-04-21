package com.bank.bank.Model.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.bank.Model.enitit.Transaction;

public interface trasaksiRepo extends CrudRepository <Transaction, Long>  {
    
}
