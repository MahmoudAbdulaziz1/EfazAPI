package com.taj.controller;

import com.taj.model.LoginModel;
import com.taj.repository.LoginRepo;
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


    /**
     * add email of company to login table if email is found
     * in registration and is activated by admin and password matched
     *
     * @param model
     * @return object of logged company
     */
    @PostMapping("/loginUser")
    public LoginModel loginUsers(@RequestBody LoginModel model) {
        return loginRepo.loginUser(model.getUser_email(), model.getUser_password(),
                model.getIs_active(), model.getLogin_type(), model.getLogin_role(), model.getLogin_token());
    }

    /**
     * get all login companies
     *
     * @return list of logged companies
     */

    @GetMapping("/getAll")
    public List<LoginModel> getLoggedUser() {
        return loginRepo.getLoggedUsers();
    }


    /**
     * get logged user data by login id
     *
     * @param id
     * @return logged user object
     */

    @GetMapping("/get/{id}")
    public LoginModel getUser(@PathVariable int id) {
        return loginRepo.getLoggedUser(id);
    }

    /**
     * check if company email is exist in login db
     *
     * @param model
     * @return true if exist in table or not if not exist
     */

    @PostMapping("/isLogged")
    public boolean isLogged(@RequestBody LoginModel model) {

        return loginRepo.isLogged(model.getUser_email(), model.getUser_password(), model.getLogin_type());
    }

    /**
     * get logged data
     *
     * @param model
     * @return logged data by email, password and loginType
     */

    @PostMapping("/getLoginId")
    public LoginModel getLoggedId(@RequestBody LoginModel model) {

        return loginRepo.getLoggedId(model.getUser_email(), model.getUser_password(), model.getLogin_type());
    }


    /**
     * update user password
     *
     * @param model
     */
    @PutMapping("/updatePassword")
    public int updatePassword(@RequestBody LoginModel model) {
        return loginRepo.updatePassword(model.getLogin_id(), model.getUser_email(), model.getUser_password());

    }
//
//    /**
//     *
//     * @param model
//     */
//    @PutMapping("/updateActiveState")
//    public void updateActiveState(@RequestBody LoginModel model){
//        loginRepo.updateActiveState(model.getLogin_id(), model.getIs_active());
//    }

    /**
     * delete company email from login
     *
     * @param model
     */
    @PutMapping("/deleteUser")
    public void deleteUser(@RequestBody LoginModel model) {

        loginRepo.deleteUser(model.getLogin_id(), model.getUser_email());
    }


    /**
     * active user when login
     * use login id
     *
     * @param id
     */
    @PutMapping("/activeUser/{id}")
    public void activeUser(@PathVariable int id) {
        loginRepo.activeLogin(id);
    }

    /**
     * get all inactive companies
     *
     * @return list of inactive now companies
     */
    @GetMapping("/inactiveCompanies")
    public List<LoginModel> getInActiveCompanies() {
        return loginRepo.getInActiveCompanies();
    }

    /**
     * get all active companies
     *
     * @return list of active now companies
     */
    @GetMapping("/activeCompanies")
    public List<LoginModel> getActiveCompanies() {
        return loginRepo.getActiveCompanies();
    }

    /**
     * active user when login
     * use login id
     *
     * @param id
     */
    @PutMapping("/inActiveUser/{id}")
    public void inActiveUser(@PathVariable int id) {
        loginRepo.inActiveLogin(id);
    }

}
