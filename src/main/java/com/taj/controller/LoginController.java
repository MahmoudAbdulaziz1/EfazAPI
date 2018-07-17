package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.LoginModel;
import com.taj.repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    ObjectMapper mapper;


    /**
     * add email of company to login table if email is found
     * in registration and is activated by admin and password matched
     *
     * @param model
     * @return object of logged company
     */
    @PostMapping("/loginUser")
    public ObjectNode loginUsers(@Valid @RequestBody LoginModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }

        loginRepo.loginUser(model.getUser_email(), model.getUser_password(),
                model.getIs_active(), model.getLogin_type(), model.getLogin_role(), model.getLogin_token());
        ObjectNode objectNode = mapper.createObjectNode();
        //objectNode.put("user_email", model.getUser_email());
        objectNode.put("user_email", model.getUser_email());
        objectNode.put("user_password", model.getUser_password());
        objectNode.put("is_active", model.getIs_active());
        objectNode.put("login_type", model.getLogin_type());
        objectNode.put("login_role", model.getLogin_role());
        objectNode.put("login_token", model.getLogin_token());

        return objectNode;

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
    public ObjectNode isLogged(@Valid @RequestBody LoginModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        boolean check = loginRepo.isLogged(model.getUser_email(), model.getUser_password(), model.getLogin_type());
        if (check == true)
        {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");
            return objectNode;
        }

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
    public ObjectNode updatePassword(@Valid @RequestBody LoginModel model, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res =  loginRepo.updatePassword(model.getLogin_id(), model.getUser_email(), model.getUser_password());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("login_id", model.getLogin_id());
            objectNode.put("user_email", model.getUser_email());
            objectNode.put("user_password", model.getUser_password());
            objectNode.put("is_active", model.getIs_active());
            objectNode.put("login_type", model.getLogin_type());
            objectNode.put("login_role", model.getLogin_role());
            objectNode.put("login_token", model.getLogin_token());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
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
    public ObjectNode deleteUser(@Valid @RequestBody LoginModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = loginRepo.deleteUser(model.getLogin_id(), model.getUser_email());


        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("login_id", model.getLogin_id());
            objectNode.put("user_email", model.getUser_email());
            objectNode.put("user_password", model.getUser_password());
            objectNode.put("is_active", model.getIs_active());
            objectNode.put("login_type", model.getLogin_type());
            objectNode.put("login_role", model.getLogin_role());
            objectNode.put("login_token", model.getLogin_token());

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }


    /**
     * active user when login
     * use login id
     *
     * @param id
     */
    @PutMapping("/activeUser/{id}")
    public int activeUser(@PathVariable int id) {
        return loginRepo.activeLogin(id);
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
    public int  inActiveUser(@PathVariable int id) {
        return loginRepo.inActiveLogin(id);
    }

}
