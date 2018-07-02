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


    /**
     * register company
     *
     * @param registrationModel
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUserRegistration(@RequestBody RegistrationModel registrationModel) {

        String encodedPassword = bCryptPasswordEncoder.encode(registrationModel.getRegisteration_password());
        registrationRepo.addUser(registrationModel.getRegisteration_email(), encodedPassword,
                registrationModel.getRegisteration_username(), registrationModel.getRegisteration_phone_number(),
                registrationModel.getRegistration_organization_name(), registrationModel.getRegistration_address_desc(),
                registrationModel.getRegistration_website_url(), registrationModel.getRegistration_is_school(),
                registrationModel.getRegistration_isActive());

    }

    /**
     * get all registered companies data
     *
     * @return List of registered company data
     */

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

    @GetMapping("/get/{id}")
    public RegistrationModel getUser(@PathVariable int id) {
        return registrationRepo.getUser(id);
    }

    /**
     * update registered data
     *
     * @param model
     * @return
     */

    @PutMapping("/update")
    public int updateUser(@RequestBody RegistrationModel model) {
        return registrationRepo.updateUser(model.getRegistration_id(), model.getRegisteration_email(), model.getRegisteration_password(),
                model.getRegisteration_username(), model.getRegisteration_phone_number(), model.getRegistration_organization_name(), model.getRegistration_address_desc(),
                model.getRegistration_website_url(), model.getRegistration_is_school(), model.getRegistration_isActive());
    }

    /**
     * delete registered company by register id
     *
     * @param id
     * @return
     */
    @PutMapping("/delete/{id}")
    public int deleteUser(@PathVariable int id) {
        return registrationRepo.deleteUser(id);
    }


    /**
     * get list of all inactive companies
     *
     * @return
     */
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
    @GetMapping("/confirm/{id}")
    public void confirmEmail(@PathVariable int id) {
        registrationRepo.confirmEmail(id);
    }


}
