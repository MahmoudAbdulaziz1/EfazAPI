package com.taj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taj.model.SchoolProfileModel;
import com.taj.repository.SchoolProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
//@RequestMapping("/evvaz/school/profile")
@RequestMapping("/school/profile")
@RestController
@CrossOrigin
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolProfileController {


    @Autowired
    ObjectMapper mapper;
    @Autowired
    private SchoolProfileRepo repo;

    /**
     * add profile data to db
     *
     * @param model
     * @return 1 if added and 0  if not
     */
    @PostMapping("/addProfile")
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public ResponseEntity<ObjectNode> AddUserProfile(@Valid @RequestBody SchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            objectNode.put("details", errors.getAllErrors().toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
        if (repo.isExistInLogin(model.getSchool_id(), "school")) {

            if (!repo.isExist(model.getSchool_id())) {
                int res = repo.addSchoolProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                        model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                        model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat(), model.getSchool_cover_image());
                if (res == 1) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("school_name", model.getSchool_name());
                    objectNode.put("school_logo_image", model.getSchool_logo_image());
                    objectNode.put("school_address", model.getSchool_address());
                    objectNode.put("school_service_desc", model.getSchool_service_desc());
                    objectNode.put("school_link_youtube", model.getSchool_link_youtube());
                    objectNode.put("school_website_url", model.getSchool_website_url());
                    objectNode.put("school_lng", model.getSchool_lng());
                    objectNode.put("school_lat", model.getSchool_lat());
                    objectNode.put("school_cover_image", model.getSchool_cover_image());
                    return ResponseEntity.status(HttpStatus.OK).body(objectNode);
                } else {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("status", 400);
                    objectNode.put("message", "not success");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
                }
            } else {
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("status", 400);
                objectNode.put("message", "id Already has profile");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
            }


        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("status", 400);
            objectNode.put("message", "id not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    /**
     * get all school profiles
     *
     * @return list of profiles
     */

    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getProfiles")
    public List<SchoolProfileModel> getProfiles() {
        return repo.getSchoolSProfiles();
    }


    /**
     * get single profile using school id
     *
     * @param id
     * @return school profile by id
     */
    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getProfile/{id}")
    public SchoolProfileModel getProfile(@PathVariable int id) {
        return repo.getSchoolProfile(id);
    }

    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/updateProfile")
    public ResponseEntity<ObjectNode> updateProfile(@Valid @RequestBody SchoolProfileModel model, Errors errors) {

        if (errors.hasErrors()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("state", 400);
            objectNode.put("message", "Validation Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);

        }

        int res = repo.updateProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat(), model.getSchool_cover_image());

        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("school_name", model.getSchool_name());
            objectNode.put("school_logo_image", model.getSchool_logo_image());
            objectNode.put("school_address", model.getSchool_address());
            objectNode.put("school_service_desc", model.getSchool_service_desc());
            objectNode.put("school_link_youtube", model.getSchool_link_youtube());
            objectNode.put("school_website_url", model.getSchool_website_url());
            objectNode.put("school_lng", model.getSchool_lng());
            objectNode.put("school_lat", model.getSchool_lat());
            objectNode.put("school_cover_image", model.getSchool_cover_image());
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }


    }

    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/profileExist/{id}")
    public int isExist(@PathVariable int id) {
        return repo.checkSchoolProfile(id);
    }

    //@PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ObjectNode> deleteSchoolProfile(@PathVariable int id) {
        int res = repo.deleteSchoolProfile(id);
        if (res == 1) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("messsage", "success");
            return ResponseEntity.status(HttpStatus.OK).body(objectNode);
        } else {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("value", "not success");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectNode);
        }
    }


}
