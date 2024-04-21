package com.bank.bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.bank.Model.enitit.Nasabah;
import com.bank.bank.Model.enitit.Transaction;
import com.bank.bank.dto.Response;
import com.bank.bank.services.NasabahSerice;
import com.bank.bank.services.TransaksiService;


@RestController
@RequestMapping("/api/transaksi")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;
    
    @Autowired
    private NasabahSerice nasabahService;

    @PostMapping
    public Response<Object> createTransaksi(@RequestBody Transaction transaksi) {
        Response<Object> res = new Response<>();
        
        // Cek apakah pengirim dan penerima ada
        Nasabah pengirim = nasabahService.findOne(transaksi.getPengirimId());
        Nasabah penerima = nasabahService.findOne(transaksi.getPenerimaId());
        if (pengirim == null || penerima == null) {
            res.setMsg("Invalid sender or receiver");
            res.setStatus(HttpStatus.BAD_REQUEST.toString());
            return res;
        }
        
        // Cek saldo cukup untuk transaksi
        if (pengirim.getSaldo() < transaksi.getJumlah()) {
            res.setMsg("Insufficient balance");
            res.setStatus(HttpStatus.BAD_REQUEST.toString());
            return res;
        }
        
        // Kurangi saldo pengirim
        pengirim.setSaldo(pengirim.getSaldo() - transaksi.getJumlah());
        nasabahService.create(pengirim);
        
        // Tambahkan saldo penerima
        penerima.setSaldo(penerima.getSaldo() + transaksi.getJumlah());
        nasabahService.create(penerima);
        
        // Buat transaksi baru
        Transaction createdTransaksi = transaksiService.createTransaksi(transaksi);
        
        res.setMsg("Transaction created successfully");
        res.setStatus(HttpStatus.CREATED.toString());
        res.setPayload(createdTransaksi);
        return res;
    }

    @GetMapping
    public Response<Object> getAllTransaksi() {
        Response<Object> res = new Response<>();
        Iterable<Transaction> transaksis = transaksiService.getAllTransaksi();
        res.setMsg("Success");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(transaksis);
        return res;
    }

    @GetMapping("/{id}")
    public Response<Object> getTransaksiById(@PathVariable Long id) {
        Response<Object> res = new Response<>();
        Transaction transaksi = transaksiService.getTransaksiById(id);
        if (transaksi == null) {
            res.setMsg("Transaction not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            return res;
        }
        res.setMsg("Success");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(transaksi);
        return res;
    }

    @DeleteMapping("/{id}")
    public Response<Object> removeTransaksiById(@PathVariable Long id) {
        Response<Object> res = new Response<>();
        transaksiService.removeTransaksiById(id);
        res.setMsg("Transaction deleted successfully");
        res.setStatus(HttpStatus.NO_CONTENT.toString());
        return res;
    }

    @PutMapping("/{id}")
    public Response<Object> updateTransaksi(@PathVariable Long id, @RequestBody Transaction transaksi) {
        Response<Object> res = new Response<>();
        Transaction existingTransaksi = transaksiService.getTransaksiById(id);
        if (existingTransaksi == null) {
            res.setMsg("Transaction not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            return res;
        }
        
        existingTransaksi.setJumlah(transaksi.getJumlah()); 
        
        Transaction updatedTransaksi = transaksiService.createTransaksi(existingTransaksi);
        res.setMsg("Transaction updated successfully");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(updatedTransaksi);
        return res;
    }
}
