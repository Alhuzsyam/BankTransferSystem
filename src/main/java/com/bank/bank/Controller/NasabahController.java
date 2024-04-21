package com.bank.bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bank.Model.enitit.Nasabah;
import com.bank.bank.dto.Response;
import com.bank.bank.services.NasabahSerice;

@RestController
@RequestMapping("/api/nasabah")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class NasabahController {

    @Autowired
    private NasabahSerice nasabahService;

    @PostMapping
    public ResponseEntity<Response<Object>> createNasabah(@RequestBody Nasabah nasabah) {
        Response<Object> res = new Response<>();
        // Check if username is unique
        if (nasabahService.existsByUsername(nasabah.getUsername())) {
            res.setMsg("Username already exists");
            res.setStatus(HttpStatus.BAD_REQUEST.toString());
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        Nasabah createdNasabah = nasabahService.create(nasabah);
        res.setMsg("Nasabah created successfully");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(createdNasabah);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<Nasabah> getAllNasabah() {
        return nasabahService.getall();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Object>> getNasabahById(@PathVariable Long id) {
        Response<Object> res = new Response<>();
        Nasabah nasabah = nasabahService.findOne(id);
        if (nasabah == null) {
            res.setMsg("Nasabah not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        res.setMsg("Nasabah found");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(nasabah);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Response<Object>> deleteNasabahById(@PathVariable Long id) {
    Response<Object> res = new Response<>();
    Nasabah nasabah = nasabahService.findOne(id);
    if (nasabah == null) {
        res.setMsg("Nasabah not found");
        res.setStatus(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
    nasabahService.removeNasabahById(id);
    res.setMsg("Nasabah deleted successfully");
    res.setStatus(HttpStatus.OK.toString());
    return new ResponseEntity<>(res, HttpStatus.OK);
}


    @PutMapping("/{id}")
    public ResponseEntity<Response<Object>> updateNasabah(@PathVariable Long id, @RequestBody Nasabah nasabah) {
        Response<Object> res = new Response<>();
        // Check if nasabah exists
        Nasabah existingNasabah = nasabahService.findOne(id);
        if (existingNasabah == null) {
            res.setMsg("Nasabah not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        // Update existing nasabah with new values
        existingNasabah.setUsername(nasabah.getUsername());
        existingNasabah.setPassword(nasabah.getPassword());
        existingNasabah.setNama(nasabah.getNama());
        existingNasabah.setSaldo(nasabah.getSaldo());
        // You can add more fields to update as needed

        // Save the updated nasabah
        Nasabah updatedNasabah = nasabahService.create(existingNasabah);
        res.setMsg("Nasabah updated successfully");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(updatedNasabah);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/update-saldo/{id}")
public ResponseEntity<Response<Object>> updateSaldo(@PathVariable Long id, @RequestParam double newSaldo) {
    Response<Object> res = new Response<>();
    // Check if nasabah exists
    Nasabah existingNasabah = nasabahService.findOne(id);
    if (existingNasabah == null) {
        res.setMsg("Nasabah not found");
        res.setStatus(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
  
    existingNasabah.setSaldo(newSaldo);
    Nasabah updatedNasabah = nasabahService.create(existingNasabah);
    res.setMsg("Nasabah updated successfully");
    res.setStatus(HttpStatus.OK.toString());
    res.setPayload(updatedNasabah);
    return new ResponseEntity<>(res, HttpStatus.OK);
}


    
}

