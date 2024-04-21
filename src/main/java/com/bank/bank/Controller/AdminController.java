package com.bank.bank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.bank.Model.enitit.Admin;
import com.bank.bank.dto.Response;
import com.bank.bank.services.AdminService;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Response<Object> createAdmin(@RequestBody Admin admin) {
    Response<Object> res = new Response<>();
    
    // Check if username already exists
    if (adminService.existsByUsername(admin.getUsername())) {
        res.setMsg("Username already exists");
        res.setStatus(HttpStatus.BAD_REQUEST.toString());
        return res;
    }

    // Create admin if username is unique
    Admin createdAdmin = adminService.createAdmin(admin);
    res.setMsg("Admin created successfully");
    res.setStatus(HttpStatus.OK.toString());
    res.setPayload(createdAdmin);
    return res;
}


    @GetMapping
    public Response<Object> getAllAdmins() {
        Response<Object> res = new Response<>();
        Iterable<Admin> admins = adminService.getAllAdmins();
        res.setMsg("Success");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(admins);
        return res;
    }

    @GetMapping("/{id}")
    public Response<Object> getAdminById(@PathVariable Long id) {
        Response<Object> res = new Response<>();
        Admin admin = adminService.getAdminById(id);
        if (admin == null) {
            res.setMsg("Admin not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            return res;
        }
        res.setMsg("Success");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(admin);
        return res;
    }

    @DeleteMapping("/{id}")
    public Response<Object> removeAdminById(@PathVariable Long id) {
        Response<Object> res = new Response<>();
        adminService.removeAdminById(id);
        res.setMsg("Admin deleted successfully");
        res.setStatus(HttpStatus.NO_CONTENT.toString());
        return res;
    }

    @PutMapping("/{id}")
    public Response<Object> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Response<Object> res = new Response<>();
        Admin existingAdmin = adminService.getAdminById(id);
        if (existingAdmin == null) {
            res.setMsg("Admin not found");
            res.setStatus(HttpStatus.NOT_FOUND.toString());
            return res;
        }
        // Update existing admin with new values
        existingAdmin.setUsername(admin.getUsername());
        existingAdmin.setPassword(admin.getPassword());

        Admin updatedAdmin = adminService.createAdmin(existingAdmin);
        res.setMsg("Admin updated successfully");
        res.setStatus(HttpStatus.OK.toString());
        res.setPayload(updatedAdmin);
        return res;
    }
}
