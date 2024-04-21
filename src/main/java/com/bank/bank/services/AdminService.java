package com.bank.bank.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bank.Model.enitit.Admin;
import com.bank.bank.Model.repository.AdminRepos;

@Service
public class AdminService {

    @Autowired
    private AdminRepos adminRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Iterable<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElse(null);
    }

    public void removeAdminById(Long id) {
        adminRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
}

