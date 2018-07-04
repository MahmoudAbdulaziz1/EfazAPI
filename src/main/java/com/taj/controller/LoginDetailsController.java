package com.taj.controller;

import com.taj.model.LoginDetailsModel;
import com.taj.repository.LoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Taj 51 on 6/11/2018.
 */
@RequestMapping("/details")
@RestController
@CrossOrigin
public class LoginDetailsController {

    @Autowired
    LoginDetailsRepo repo;


    /**
     * add details to table
     *
     * @param detailsModel
     */
    @PostMapping("/add")
    public void addLoginDetails(@RequestBody LoginDetailsModel detailsModel) {
        repo.addLoginDetails(detailsModel.getLogin_id(), detailsModel.getIs_school(), detailsModel.getLgoin_time(),
                detailsModel.getIp_address(), detailsModel.getIs_mobill());

    }


    /**
     * @return list of all details in db
     */
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

    @GetMapping("/getCompanyDetails/{id}")
    public List<LoginDetailsModel> getLoginDetailsForCompany(@PathVariable int id) {
        return repo.getDetailForCompany(id);
    }
}
