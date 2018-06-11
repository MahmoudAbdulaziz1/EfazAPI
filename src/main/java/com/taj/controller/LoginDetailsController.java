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


    @PostMapping("/add")
    public void addLoginDetails(@RequestBody LoginDetailsModel detailsModel){
        repo.addLoginDetails(detailsModel.getLogin_id(), detailsModel.getIs_school(), detailsModel.getLgoin_time(),
                detailsModel.getIp_address(), detailsModel.getIs_mobill());

    }

    @GetMapping("/getAll")
    public List<LoginDetailsModel> getLoginDetails(){
        return repo.getDetails();
    }

    @GetMapping("/get/{id}")
    public LoginDetailsModel getLoginDetail(@PathVariable int id){
        return repo.getDetail(id);
    }
}
