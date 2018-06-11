package com.taj.controller;

import com.taj.model.RegistrationModel;
import com.taj.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 5/30/2018.
 */
@RequestMapping("/register")
@RestController
@CrossOrigin
public class RegistrationController {

    @Autowired
    RegistrationRepo registrationRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUserRegistration(@RequestBody RegistrationModel registrationModel){

        String encodedPassword = bCryptPasswordEncoder.encode(registrationModel.getRegisteration_password());
        registrationRepo.addUser(registrationModel.getRegisteration_email(), encodedPassword,
                 registrationModel.getRegisteration_username(), registrationModel.getRegisteration_phone_number(),
                registrationModel.getRegistration_organization_name(), registrationModel.getRegistration_address_desc(),
                registrationModel.getRegistration_website_url(), registrationModel.getRegistration_is_school(),
                registrationModel.getRegistration_isActive());
    }

    @GetMapping("/getAll")
    public List<RegistrationModel> getAllUsers(){
        return registrationRepo.getUsers();
    }

    @GetMapping("/get/{id}")
    public RegistrationModel getUser(@PathVariable int id){
        return registrationRepo.getUser(id);
    }

    @PutMapping("/update/{id}")
    public int updateUser(@PathVariable int id, @RequestBody RegistrationModel model){
        return registrationRepo.updateUser(id, model.getRegisteration_email(), model.getRegisteration_password(),
                model.getRegistration_organization_name(), model.getRegisteration_phone_number(), model.getRegistration_organization_name(), model.getRegistration_address_desc(),
                model.getRegistration_website_url(), model.getRegistration_is_school(), model.getRegistration_isActive());
    }

    @PutMapping("/delete/{id}")
    public int deleteUser(@PathVariable int id){
        return registrationRepo.deleteUser(id);
    }


}
