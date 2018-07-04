package com.taj.controller;

import com.taj.model.SchoolProfileModel;
import com.taj.repository.SchoolProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@RequestMapping("/schoolProfile")
@RestController
@CrossOrigin
public class SchoolProfileController {


    @Autowired
    private SchoolProfileRepo repo;

    /**
     * add profile data to db
     *
     * @param model
     * @return 1 if added and 0  if not
     */
    @PostMapping("/addProfile")
    public int AddUserProfile(@RequestBody SchoolProfileModel model) {

        return repo.addSchoolProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(),model.getSchool_lng(), model.getSchool_lat());


    }

    /**
     * get all school profiles
     *
     * @return list of profiles
     */

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

    @GetMapping("/getProfile/{id}")
    public SchoolProfileModel getProfile(@PathVariable int id) {
        return repo.getSchoolProfile(id);
    }


    @PutMapping("/updateProfile")
    public int updateProfile(@RequestBody SchoolProfileModel model) {

        return repo.updateProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat());
    }


    @GetMapping("/profileExist/{id}")
    public int isExist(@PathVariable int id) {
        return repo.checkSchoolProfile(id);
    }

    @PutMapping("/delete/{id}")
    public int deleteSchoolProfile(@PathVariable int id) {
        return repo.deleteSchoolProfile(id);
    }


}
