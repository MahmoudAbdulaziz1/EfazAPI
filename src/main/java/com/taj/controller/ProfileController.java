package com.taj.controller;

import com.taj.model.CompanyProfileModel;
import com.taj.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 5/31/2018.
 */
@RequestMapping("/profile")
@RestController
@CrossOrigin
public class ProfileController {

    @Autowired
    ProfileRepo profileRepo;

    /**
     * add profile to db
     *
     * @param model
     * @return 1 if added or 0  if false
     */

    @PostMapping("/addProfile")
    public int AddUserProfile(@RequestBody CompanyProfileModel model) {

        return profileRepo.addProfile(model.getCompany_id(), model.getCompany_name(), model.getCompany_logo_image(),
                model.getCompany_address(), model.getCompany_service_desc(), model.getCompany_link_youtube(),
                model.getCompany_website_url());


    }

    /**
     * get all profiles data
     *
     * @return list of profiles
     */

    @GetMapping("/getProfiles")
    public List<CompanyProfileModel> getProfiles() {
        return profileRepo.getProfiles();
    }

    /**
     * update profile
     *
     * @param model
     * @return 1 if updated and 0 if not updated
     */

    @PutMapping("/updateProfile")
    public int updateProfile(@RequestBody CompanyProfileModel model) {

        return profileRepo.updateProfile(model.getCompany_id(), model.getCompany_name(), model.getCompany_logo_image(),
                model.getCompany_address(), model.getCompany_service_desc(), model.getCompany_link_youtube(),
                model.getCompany_website_url());
    }

    /**
     * get profile date by its id
     *
     * @param id
     * @return profile model
     */

    @GetMapping("/getProfile/{id}")
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
    public int isExist(@PathVariable int id) {
        return profileRepo.CheckProfile(id);
    }

}
