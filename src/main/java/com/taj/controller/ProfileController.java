package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.CompanyProfileModel;
import com.taj.model.GetAllCompanies;
import com.taj.model.GetCompanyById;
import com.taj.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MahmoudAhmed on 5/31/2018.
 */
@RequestMapping("/profile")
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
    public ResponseEntity<ObjectNode> AddUserProfile(@Valid @RequestBody CompanyProfileModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (profileRepo.isExist(model.getCompany_id())){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "already has profile in this id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }else {
            int res =  profileRepo.addProfile(model.getCompany_id() ,model.getCompany_name(), model.getCompany_logo_image(),
                    model.getCompany_address(), model.getCompany_category_id(), model.getCompany_link_youtube(),
                    model.getCompany_website_url(), model.getCompany_lng(), model.getCompany_lat());

            if (res == 1){
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("state", 200);
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("company_name", model.getCompany_name());
                objectNode.put("company_logo_image", model.getCompany_logo_image());
                objectNode.put("company_address", model.getCompany_address());
                objectNode.put("company_category_id", model.getCompany_category_id());
                objectNode.put("company_link_youtube", model.getCompany_link_youtube());
                objectNode.put("company_website_url", model.getCompany_website_url());
                objectNode.put("company_lng", model.getCompany_lng());
                objectNode.put("company_lat", model.getCompany_lat());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            }else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }




    }

    /**
     * get all profiles data
     *
     * @return list of profiles
     */

    @GetMapping("/getAll")
    public ResponseEntity<GetAllCompanies> getProfiles() {

        List<CompanyProfileModel> list = profileRepo.getProfiles();
        ObjectNode objectNode = mapper.createObjectNode();
        GetAllCompanies getAllCompanies = new GetAllCompanies("200", list);
        return  ResponseEntity.status(HttpStatus.OK).body(getAllCompanies);

    }

    /**
     * update profile
     *
     * @param model
     * @return 1 if updated and 0 if not updated
     */

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public ResponseEntity<ObjectNode> updateProfile(@Valid @RequestBody CompanyProfileModel model, Errors errors) {

        if (errors.hasErrors()){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            //objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (profileRepo.isExist(model.getCompany_id())){
            int res = profileRepo.updateProfile(model.getCompany_id(), model.getCompany_name(), model.getCompany_logo_image(),
                    model.getCompany_address(), model.getCompany_category_id(), model.getCompany_link_youtube(),
                    model.getCompany_website_url(), model.getCompany_lng(), model.getCompany_lat());
            if (res == 1){
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("company_id", model.getCompany_id());
                objectNode.put("company_name", model.getCompany_name());
                objectNode.put("company_logo_image", model.getCompany_logo_image());
                objectNode.put("company_address", model.getCompany_address());
                objectNode.put("company_category_id", model.getCompany_category_id());
                objectNode.put("company_link_youtube", model.getCompany_link_youtube());
                objectNode.put("company_website_url", model.getCompany_website_url());
                objectNode.put("company_lng", model.getCompany_lng());
                objectNode.put("company_lat", model.getCompany_lat());
                return ResponseEntity.status(HttpStatus.OK).body(objectNode);
            }else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("value", "not success");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 200);
            objectNode.put("value", "not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }




    }

    /**
     * get profile date by its id
     *
     * @param id
     * @return profile model
     */

    @GetMapping("/get/{id}")
    public ResponseEntity<GetCompanyById> getProfile(@PathVariable int id) {
        List<CompanyProfileModel> model =  profileRepo.getProfile(id);
        if (model.size()>0) {
            return ResponseEntity.status(HttpStatus.OK).body(new GetCompanyById("200", model));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetCompanyById("400", null));
        }
    }

    @GetMapping("/get/{id}/category")
    public ResponseEntity<GetCompanyById> getProfileByCategory(@PathVariable int id) {
        List<CompanyProfileModel> model =  profileRepo.getProfileByCategory(id);
        if (model.size()>0) {
            return ResponseEntity.status(HttpStatus.OK).body(new GetCompanyById("200", model));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GetCompanyById("400", null));
        }
    }


    /**
     * check profile if exist
     *
     * @param id
     * @return if >0  true else  0 not found
     */
    @GetMapping("/profileExist/{id}")
    public ResponseEntity<ObjectNode> isExist(@PathVariable int id) {
        int res = profileRepo.CheckProfile(id);
        if (res ==1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 200);
            objectNode.put("message", "Exist");
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Not Exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        //return profileRepo.CheckProfile(id);
    }

}
