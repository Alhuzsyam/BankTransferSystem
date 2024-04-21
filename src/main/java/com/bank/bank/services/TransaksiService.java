package com.bank.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Model.enitit.Transaction;
import com.bank.bank.Model.repository.trasaksiRepo;

import java.util.Optional;

@Service
public class TransaksiService {

    @Autowired
    private trasaksiRepo transaksiRepository;

    public Transaction createTransaksi(Transaction transaksi) {
        return transaksiRepository.save(transaksi);
    }

    public Iterable<Transaction> getAllTransaksi() {
        return transaksiRepository.findAll();
    }

    public Transaction getTransaksiById(Long id) {
        Optional<Transaction> transaksi = transaksiRepository.findById(id);
        return transaksi.orElse(null);
    }

    public void removeTransaksiById(Long id) {
        transaksiRepository.deleteById(id);
    }
}

