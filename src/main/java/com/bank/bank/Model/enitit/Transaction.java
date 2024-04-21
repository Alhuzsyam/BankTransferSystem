package com.bank.bank.Model.enitit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaksi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaksi;

    @Column(name = "pengirim_id")
    private Long pengirimId;

    @Column(name = "penerima_id")
    private Long penerimaId;

    @Column(name = "jumlah")
    private double jumlah;

    @Column(name = "tanggal")
    private Date tanggal;
}

