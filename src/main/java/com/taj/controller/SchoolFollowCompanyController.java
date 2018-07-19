package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolFollowCompany;
import com.taj.model.SchoolSeeRequest;
import com.taj.repository.SchoolFollowCompanyRepo;
import com.taj.repository.SchoolSeeRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 7/19/2018.
 */

@RequestMapping("/evvaz/follow")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolFollowCompanyController {



    @Autowired
    SchoolFollowCompanyRepo repo;
    @Autowired
    ObjectMapper mapper;


    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    @PostMapping("/add")
    public ObjectNode addFollower(@RequestBody @Valid SchoolFollowCompany model, Errors errors){
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res =  repo.addFollower(model.getOrganization_id(), model.getFollower_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            //objectNode.put("Follow_id", model.getFollow_id());
            objectNode.put("Organization_id", model.getOrganization_id());
            objectNode.put("Follower_id", model.getFollower_id());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolFollowCompany> getAllFollowers(){
        return repo.getAllFollowers();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public SchoolFollowCompany getById(@PathVariable int id){
        return repo.getById(id);
    }

    @GetMapping("/get/school/following/{schoolId}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolFollowCompany> getAllSchoolFollowing(@PathVariable int schoolId){
        return repo.getAllSchoolFollowing(schoolId);
    }

    @GetMapping("/get/followers/{companyId}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolFollowCompany> getCompanyAllFollowers(@PathVariable int companyId){
        return repo.getCompanyAllFollowers(companyId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode updateSchoolFollowCompany(@Valid @RequestBody SchoolFollowCompany model, Errors errors){
        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return objectNode;
        }
        int res = repo.updateSchoolFollowCompany(model.getFollow_id(), model.getOrganization_id(), model.getFollower_id());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("Follow_id", model.getFollow_id());
            objectNode.put("Organization_id", model.getOrganization_id());
            objectNode.put("Follower_id", model.getFollower_id());

            return objectNode;
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }
    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public ObjectNode deleteSchoolFollowCompany(@PathVariable int id){
        int res = repo.deleteSchoolFollowCompany(id);
        if (res == 1){
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "success");

            return objectNode;
        }else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return objectNode;
        }
    }



}
