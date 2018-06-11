package com.taj.controller;

import com.taj.model.LoginModel;
import com.taj.repository.LoginRepo;
import com.taj.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
@RequestMapping("/login")
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    LoginRepo loginRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/loginUser")
    public LoginModel loginUsers(@RequestBody LoginModel model){
        return loginRepo.loginUser(model.getUser_email(), model.getUser_password(),
                model.getIs_active(), model.getLogin_type());
    }

    @GetMapping("/getLogged")
    public List<LoginModel> getLoggedUser(){
        return loginRepo.getLoggedUsers();
    }

    @PutMapping("/deleteLogged/{id}")
    public int deleteLogged(@PathVariable int id){
        return loginRepo.deleteLoggedUser(id);
    }

    @GetMapping("/getUser/{id}")
    public LoginModel getUser(@PathVariable int id){
        return loginRepo.getLoggedUser(id);
    }

}
