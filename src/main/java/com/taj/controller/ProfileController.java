package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanyProfileModel;
import com.taj.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by MahmoudAhmed on 5/31/2018.
 */
@RequestMapping("/evvaz/profile")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProfileController {

    @Autowired
    ProfileRepo profileRepo;
    @Autowired
    ObjectMapper mapper;

    /**
     * add profile to db
     *
     * @param model
     * @return 1 if added or 0  if false
     */

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode AddUserProfile(@Valid @RequestBody CompanyProfileModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }


        int res =  profileRepo.addProfile(model.getCompany_name(), model.getCompany_logo_image(),
                model.getCompany_address(), model.getCompany_service_desc(), model.getCompany_link_youtube(),
                model.getCompany_website_url(), model.getCompany_lng(), model.getCompany_lat());

        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("company_name", model.getCompany_name());
            objectNode.put("company_logo_image", model.getCompany_logo_image());
            objectNode.put("company_address", model.getCompany_address());
            objectNode.put("company_service_desc", model.getCompany_service_desc());
            objectNode.put("company_link_youtube", model.getCompany_link_youtube());
            objectNode.put("company_website_url", model.getCompany_website_url());
            objectNode.put("company_lng", model.getCompany_lng());
            objectNode.put("company_lat", model.getCompany_lat());
            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }

    }

    /**
     * get all profiles data
     *
     * @return list of profiles
     */

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<CompanyProfileModel> getProfiles() {
        return profileRepo.getProfiles();
    }

    /**
     * update profile
     *
     * @param model
     * @return 1 if updated and 0 if not updated
     */

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode updateProfile(@Valid @RequestBody CompanyProfileModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }

        int res = profileRepo.updateProfile(model.getCompany_id(), model.getCompany_name(), model.getCompany_logo_image(),
                model.getCompany_address(), model.getCompany_service_desc(), model.getCompany_link_youtube(),
                model.getCompany_website_url(), model.getCompany_lng(), model.getCompany_lat());
        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("company_id", model.getCompany_id());
            objectNode.put("company_name", model.getCompany_name());
            objectNode.put("company_logo_image", model.getCompany_logo_image());
            objectNode.put("company_address", model.getCompany_address());
            objectNode.put("company_service_desc", model.getCompany_service_desc());
            objectNode.put("company_link_youtube", model.getCompany_link_youtube());
            objectNode.put("company_website_url", model.getCompany_website_url());
            objectNode.put("company_lng", model.getCompany_lng());
            objectNode.put("company_lat", model.getCompany_lat());
            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }


    }

    /**
     * get profile date by its id
     *
     * @param id
     * @return profile model
     */

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public CompanyProfileModel getProfile(@PathVariable int id) {
        return profileRepo.getProfile(id);
    }


    /**
     * check profile if exist
     *
     * @param id
     * @return if >0  true else  0 not found
     */
    @GetMapping("/profileExist/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int isExist(@PathVariable int id) {
        return profileRepo.CheckProfile(id);
    }

}
