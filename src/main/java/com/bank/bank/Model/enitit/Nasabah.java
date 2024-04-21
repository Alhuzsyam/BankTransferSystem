package com.bank.bank.Model.enitit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nasabah")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nasabah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nama")
    private String nama;

    @Column(name = "saldo")
    private double saldo;
    
}

