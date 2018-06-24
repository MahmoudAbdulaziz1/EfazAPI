package com.taj.controller;

import com.taj.model.RegistrationModel;
import com.taj.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
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

//        try {
//            int id =
//                    sendEmail(registrationModel.getRegisteration_email(), id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @GetMapping("/getAll")
    public List<RegistrationModel> getAllUsers(){
        return registrationRepo.getUsers();
    }

    @GetMapping("/get/{id}")
    public RegistrationModel getUser(@PathVariable int id){
        return registrationRepo.getUser(id);
    }

    @PutMapping("/update")
    public int updateUser(@RequestBody RegistrationModel model){
        return registrationRepo.updateUser(model.getRegistration_id(), model.getRegisteration_email(), model.getRegisteration_password(),
                model.getRegistration_organization_name(), model.getRegisteration_phone_number(), model.getRegistration_organization_name(), model.getRegistration_address_desc(),
                model.getRegistration_website_url(), model.getRegistration_is_school(), model.getRegistration_isActive());
    }

    @PutMapping("/delete/{id}")
    public int deleteUser(@PathVariable int id){
        return registrationRepo.deleteUser(id);
    }



    @GetMapping("/getInActive")
    public List<RegistrationModel>getInActiveCompaines(){
        return registrationRepo.getInActiveCompanies();
    }

    @PutMapping("/activeCompanyUser/{id}")
    public void activeCompanyAccount(@PathVariable int id){
        registrationRepo.activeCompanyAccount(id);
    }

    @GetMapping("/getActive")
    public List<RegistrationModel>getActiveCompaines(){
        return registrationRepo.getActiveCompanies();
    }

    @PutMapping("/inActiveCompanyUser/{id}")
    public void inActiveCompanyAccount(@PathVariable int id){
        registrationRepo.inActiveCompanyAccount(id);
    }



    @GetMapping("/confirm/{id}")
    public void confirmEmail(@PathVariable int id){
        registrationRepo.confirmEmail(id);
    }




}
