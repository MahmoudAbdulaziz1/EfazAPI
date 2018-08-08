package com.taj.controller;

import com.taj.model.CompanyBackOrderModel;
import com.taj.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 8/6/2018.
 */
@RequestMapping("/orders")
@RestController
@CrossOrigin
public class OredersController {

    @Autowired
    OrdersRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<List<CompanyBackOrderModel>> getOrdersForCompany(@PathVariable int id){
        return  repo.getOrdersForCompany(id);
    }


}
