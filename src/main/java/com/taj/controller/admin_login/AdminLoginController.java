package com.taj.controller.admin_login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.admin_login.AdminLogin;
import com.taj.model.admin_login.ChangePasswordDto;
import com.taj.repository.admin_login.AdminLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 9/30/2018.
 */
@RequestMapping("/admin/login/super")
@RestController
@CrossOrigin
public class AdminLoginController {

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    @Autowired
    AdminLoginRepo repo;

    @Autowired
    ObjectMapper mapper;

    @PostMapping("/logging")
    public ResponseEntity<ObjectNode> isLogged(@RequestBody @Valid AdminLogin model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        ObjectNode objectNode = repo.isLogged(model.getEmail(), model.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(objectNode);
    }

    @PostMapping("/add/user")
    public ResponseEntity<ObjectNode> addUser(@RequestBody @Valid AdminLogin model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        int res = repo.loginUser(model.getEmail(), model.getPassword(),
                model.getCity(), model.getArea(), model.getLng(), model.getLat(), model.getName());
        if (res > 0) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("id", res);
            objectNode.put("user_email", model.getEmail());
            objectNode.put("user_password", model.getPassword());
            objectNode.put("login_role", model.getRole());
            objectNode.put("city", model.getCity());
            objectNode.put("area", model.getArea());
            objectNode.put("lng", model.getLng());
            objectNode.put("lat", model.getLat());
            objectNode.put("name", model.getName());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else if (res == -1000) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, "400");
            objectNode.put(MESSAGE, "This Email Already Used");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put(STATUS, "400");
            objectNode.put(MESSAGE, "FAILED");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

    }

    @GetMapping("/")
    public List<AdminLogin> getAllAdmins() {
        return repo.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminLogin getAdminById(@PathVariable int id) {
        return repo.getAdminById(id);
    }

    @GetMapping("/name/{name}")
    public List<AdminLogin> getAdminByName(@PathVariable String name) {
        return repo.getAdminByName(name);
    }

    @GetMapping("/area/{area}")
    public List<AdminLogin> getAdminByArea(@PathVariable String area) {
        return repo.getAdminByArea(area);
    }

    @GetMapping("/city/{city}")
    public List<AdminLogin> getAdminByCity(@PathVariable String city) {
        return repo.getAdminByCity(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectNode> deleteAccount(@PathVariable int id) {
        int res = repo.deleteAccount(id);
        ObjectNode node = mapper.createObjectNode();
        if (res == 1) {
            node.put(STATUS, 200);
            node.put(MESSAGE, "deleted");
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else if (res == -1000) {
            node.put(STATUS, 400);
            node.put(MESSAGE, "no account for this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        } else {
            node.put(STATUS, 400);
            node.put(MESSAGE, "Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
    }

    @PutMapping("/update/password/")
    public ResponseEntity<ObjectNode> changePassword(@Valid @RequestBody ChangePasswordDto model, Errors errors) {
        ObjectNode node = mapper.createObjectNode();
        if (errors.hasErrors()) {
            node.put(STATUS, 400);
            node.put(MESSAGE, "Validation Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
        int res = repo.changePassword(model.getId(), model.getPassword());
        if (res == 1) {
            node.put(STATUS, 200);
            node.put(MESSAGE, "updated");
            return ResponseEntity.status(HttpStatus.OK).body(node);
        } else if (res == -1000) {
            node.put(STATUS, 400);
            node.put(MESSAGE, "no account for this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        } else {
            node.put(STATUS, 400);
            node.put(MESSAGE, "Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(node);
        }
    }

}
