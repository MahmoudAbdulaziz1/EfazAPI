package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.LoginIsLoggedDTO;
import com.taj.model.LoginModel;
import com.taj.model.RestPasswordModel;
import com.taj.model.UpdatePasswordModel;
import com.taj.repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ObjectMapper mapper;
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
    public ResponseEntity<ObjectNode> loginUsers(@Valid @RequestBody LoginModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }

        loginRepo.loginUser(model.getUser_email(), model.getUser_password(),
                model.getIs_active(), model.getLogin_role(), model.getLogin_token());
        ObjectNode objectNode = mapper.createObjectNode();
        //objectNode.put("user_email", model.getUser_email());
        objectNode.put("user_email", model.getUser_email());
        objectNode.put("user_password", model.getUser_password());
        objectNode.put("is_active", model.getIs_active());
        objectNode.put("login_role", model.getLogin_role());
        objectNode.put("login_token", model.getLogin_token());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(objectNode);

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
    public ResponseEntity<ObjectNode> isLogged(@Valid @RequestBody LoginIsLoggedDTO model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        ObjectNode objectNode = loginRepo.isLogged(model.getUser_email(), model.getUser_password(), model.getLogin_role());
        return ResponseEntity.status(HttpStatus.OK).body(objectNode);
    }

    /**
     * get logged data
     *
     * @param model
     * @return logged data by email, password and loginType
     */

    @PostMapping("/getLoginId")
    public LoginModel getLoggedId(@RequestBody LoginModel model) {

        return loginRepo.getLoggedId(model.getUser_email(), model.getUser_password(), model.getLogin_role());
    }


    /**
     * update user password
     *
     * @param model
     */
    @PutMapping("/updatePassword")
    public ResponseEntity<ObjectNode> updatePassword(@RequestBody @Valid UpdatePasswordModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = loginRepo.updatePassword(model.getLogin_id(), model.getUser_email(), model.getUser_password());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("message", "success");
            //objectNode.put("user_password", model.getUser_password());
//            objectNode.put("is_active", model.getIs_active());
//            objectNode.put("login_role", model.getLogin_role());
//            objectNode.put("login_token", model.getLogin_token());

            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
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
    public ResponseEntity<ObjectNode> deleteUser(@Valid @RequestBody LoginModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            //objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = loginRepo.deleteUser(model.getLogin_id(), model.getUser_email());


        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();

            objectNode.put("status", 200);
            objectNode.put("message", "success");
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
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
    public int inActiveUser(@PathVariable int id) {
        return loginRepo.inActiveLogin(id);
    }


    @PutMapping("/restPassword")
    public ResponseEntity<ObjectNode> restPassword(@RequestBody @Valid RestPasswordModel model, Errors errors) {
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        int res = loginRepo.restPassword(model.getUser_email(), model.getLogin_role());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 200);
            objectNode.put("message", "success");
            //objectNode.put("login_role", model.getLogin_role());


            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("message", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }

}
