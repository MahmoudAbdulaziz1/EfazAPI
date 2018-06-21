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

//    @PutMapping("/deleteLogged/{id}")
//    public int deleteLogged(@PathVariable int id){
//        return loginRepo.deleteLoggedUser(id);
//    }

    @GetMapping("/getUser/{id}")
    public LoginModel getUser(@PathVariable int id){
        return loginRepo.getLoggedUser(id);
    }

    @PostMapping("/isLogged")
    public boolean isLogged(@RequestBody LoginModel model){

        return loginRepo.isLogged(model.getUser_email(), model.getUser_password(), model.getLogin_type());
    }

    @PostMapping("/getLoginId")
    public LoginModel getLoggedId(@RequestBody LoginModel model){

        return loginRepo.getLoggedId(model.getUser_email(), model.getUser_password(), model.getLogin_type());
    }


    @PutMapping("/updatePassword")
    public void updatePassword(@RequestBody LoginModel model){
        loginRepo.updatePassword(model.getLogin_id(), model.getUser_email(), model.getUser_password());

    }
    @PutMapping("/updateActiveState")
    public void updateActiveState(@RequestBody LoginModel model){
        loginRepo.updateActiveState(model.getLogin_id(), model.getIs_active());
    }
    @PutMapping("/deleteUser")
    public void deleteUser(@RequestBody LoginModel model){

        loginRepo.deleteUser(model.getLogin_id(), model.getUser_email());
    }


    @PutMapping("/activeUser/{id}")
    public void activeUser(@PathVariable int id){
        loginRepo.activeLogin(id);
    }

    @GetMapping("/inactiveCompanies")
    public List<LoginModel> getInActiveCompanies(){
        return loginRepo.getInActiveCompanies();
    }
    @GetMapping("/activeCompanies")
    public List<LoginModel> getActiveCompanies(){
        return loginRepo.getActiveCompanies();
    }
    @PutMapping("/inActiveUser/{id}")
    public void inActiveUser(@PathVariable int id){
        loginRepo.inActiveLogin(id);
    }

}
