package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.LoginDetailsModel;
import com.taj.repository.LoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Taj 51 on 6/11/2018.
 */
@RequestMapping("/evvaz/details")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LoginDetailsController {

    @Autowired
    LoginDetailsRepo repo;
    @Autowired
    ObjectMapper mapper;


    /**
     * add details to table
     *
     * @param detailsModel
     */
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    @PostMapping("/add")
    public ObjectNode addLoginDetails(@Valid @RequestBody LoginDetailsModel detailsModel, Errors errors) {
        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
         int res =  repo.addLoginDetails(detailsModel.getLogin_id(), detailsModel.getIs_school(), detailsModel.getLgoin_time(),
                detailsModel.getIp_address(), detailsModel.getIs_mobill());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("login_id", detailsModel.getLogin_id());
            objectNode.put("is_school", detailsModel.getIs_school());
            objectNode.put("lgoin_time", detailsModel.getLgoin_time());
            objectNode.put("ip_address", detailsModel.getIp_address());
            objectNode.put("is_mobill", detailsModel.getIs_mobill());

            //is_mobill

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }


    /**
     * @return list of all details in db
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/getAll")
    public List<LoginDetailsModel> getLoginDetails() {
        return repo.getDetails();
    }

    /**
     * get details by details id
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get/{id}")
    public LoginDetailsModel getLoginDetail(@PathVariable int id) {
        return repo.getDetail(id);
    }

    /**
     * get list details for company by login id
     *
     * @param id
     * @return list of details for company
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/organization/details/{id}")
    public List<LoginDetailsModel> getLoginDetailsForCompany(@PathVariable int id) {
        return repo.getDetailForCompany(id);
    }
}
