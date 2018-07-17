package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.RegistrationModel;
import com.taj.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    /**
     * register company
     *
     * @param registrationModel
     */


    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectNode addUserRegistration(@Valid @RequestBody RegistrationModel registrationModel, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        String encodedPassword = bCryptPasswordEncoder.encode(registrationModel.getRegisteration_password());
        registrationRepo.addUser(registrationModel.getRegisteration_email(), encodedPassword,
                registrationModel.getRegisteration_username(), registrationModel.getRegisteration_phone_number(),
                registrationModel.getRegistration_organization_name(), registrationModel.getRegistration_address_desc(),
                registrationModel.getRegistration_website_url(), registrationModel.getRegistration_is_school(),
                registrationModel.getRegistration_isActive(), registrationModel.getRegistration_role());
        ObjectNode objectNode = mapper.createObjectNode();
        //objectNode.put("registration_id", registrationModel.getRegistration_id());
        objectNode.put("registeration_email", registrationModel.getRegisteration_email());
        objectNode.put("registeration_password", registrationModel.getRegisteration_password());
        objectNode.put("registeration_username", registrationModel.getRegisteration_username());
        objectNode.put("registeration_phone_number", registrationModel.getRegisteration_phone_number());
        objectNode.put("registration_organization_name", registrationModel.getRegistration_organization_name());
        objectNode.put("registration_address_desc", registrationModel.getRegistration_address_desc());
        objectNode.put("registration_website_url", registrationModel.getRegistration_website_url());
        objectNode.put("registration_is_school", registrationModel.getRegistration_is_school());
        objectNode.put("registration_isActive", registrationModel.getRegistration_isActive());
        objectNode.put("registration_role", registrationModel.getRegistration_role());
        return objectNode;

    }

    /**
     * get all registered companies data
     *
     * @return List of registered company data
     */

    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getAll")
    public List<RegistrationModel> getAllUsers() {
        return registrationRepo.getUsers();
    }

    /**
     * get register company data by id
     *
     * @param id
     * @return
     */

    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/get/{id}")
    public RegistrationModel getUser(@PathVariable int id) {
        return registrationRepo.getUser(id);
    }

    /**
     * update registered data
     *
     * @param registrationModel
     * @return
     */

    @PutMapping("/update")
    public ObjectNode updateUser(@Valid @RequestBody RegistrationModel registrationModel, Errors errors2) {

        if (errors2.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors2.getAllErrors().toString());
            return objectNode;
        }
        int res = registrationRepo.updateUser(registrationModel.getRegistration_id(), registrationModel.getRegisteration_email(), registrationModel.getRegisteration_password(),
                registrationModel.getRegisteration_username(), registrationModel.getRegisteration_phone_number(), registrationModel.getRegistration_organization_name(), registrationModel.getRegistration_address_desc(),
                registrationModel.getRegistration_website_url(), registrationModel.getRegistration_is_school(), registrationModel.getRegistration_isActive(), registrationModel.getRegistration_role());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("registration_id", registrationModel.getRegistration_id());
            objectNode.put("registeration_email", registrationModel.getRegisteration_email());
            objectNode.put("registeration_password", registrationModel.getRegisteration_password());
            objectNode.put("registeration_username", registrationModel.getRegisteration_username());
            objectNode.put("registeration_phone_number", registrationModel.getRegisteration_phone_number());
            objectNode.put("registration_organization_name", registrationModel.getRegistration_organization_name());
            objectNode.put("registration_address_desc", registrationModel.getRegistration_address_desc());
            objectNode.put("registration_website_url", registrationModel.getRegistration_website_url());
            objectNode.put("registration_is_school", registrationModel.getRegistration_is_school());
            objectNode.put("registration_isActive", registrationModel.getRegistration_isActive());
            objectNode.put("registration_role", registrationModel.getRegistration_role());
            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }


    }

    /**
     * delete registered company by register id
     *
     * @param id
     * @return
     */
    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @PutMapping("/delete/{id}")
    public int deleteUser(@PathVariable int id) {
        return registrationRepo.deleteUser(id);
    }


    /**
     * get list of all inactive companies
     *
     * @return
     */
    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getInActive")
    public List<RegistrationModel> getInActiveCompaines() {
        return registrationRepo.getInActiveCompanies();
    }

//    /**
//     * active user by admin and send mail to him
//     *
//     * @param id
//     */
//    @PutMapping("/activeCompanyUser/{id}")
//    public void activeCompanyAccount(@PathVariable int id) {
//        registrationRepo.activeCompanyAccount(id);
//    }

    /**
     * get list of all active companies
     *
     * @return
     */

    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getActive")
    public List<RegistrationModel> getActiveCompaines() {
        return registrationRepo.getActiveCompanies();
    }

//    /**
//     * inactive user by admin and send mail to him
//     *
//     * @param id
//     */
//    @PutMapping("/inActiveCompanyUser/{id}")
//    public void inActiveCompanyAccount(@PathVariable int id) {
//        registrationRepo.inActiveCompanyAccount(id);
//    }

    /**
     * confirm company account and send mail to user to inform him
     *
     * @param id
     */
    //@PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/confirm/{id}")
    public int confirmEmail(@PathVariable int id) {
        return registrationRepo.confirmEmail(id);
    }


}
