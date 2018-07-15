package com.taj.controller;

import com.taj.model.SchoolProfileModel;
import com.taj.repository.SchoolProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@RequestMapping("/evvaz/school/profile")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
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

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
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
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/getProfile/{id}")
    public SchoolProfileModel getProfile(@PathVariable int id) {
        return repo.getSchoolProfile(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/updateProfile")
    public int updateProfile(@RequestBody SchoolProfileModel model) {

        return repo.updateProfile(model.getSchool_id(), model.getSchool_name(), model.getSchool_logo_image(),
                model.getSchool_address(), model.getSchool_service_desc(), model.getSchool_link_youtube(),
                model.getSchool_website_url(), model.getSchool_lng(), model.getSchool_lat());
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @GetMapping("/profileExist/{id}")
    public int isExist(@PathVariable int id) {
        return repo.checkSchoolProfile(id);
    }

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PutMapping("/delete/{id}")
    public int deleteSchoolProfile(@PathVariable int id) {
        return repo.deleteSchoolProfile(id);
    }


}
