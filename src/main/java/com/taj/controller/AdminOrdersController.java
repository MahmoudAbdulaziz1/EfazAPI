package com.taj.controller;

import com.taj.model.AdminHistoryOrdersModel;
import com.taj.model.AdminOrdersModel;
import com.taj.model.AdminSingleOrderHistoryModel;
import com.taj.model.AdminSingleOrderModel;
import com.taj.repository.AdminOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 9/3/2018.
 */
@RequestMapping("/admin/orders")
@RestController
@CrossOrigin
public class AdminOrdersController {

    @Autowired
    AdminOrdersRepo repo;

    @GetMapping("/")
    public List<AdminOrdersModel> getAllOrders(){
        return repo.getAllOrders();
    }
    @GetMapping("/{id}")
    public AdminSingleOrderModel getOrder(@PathVariable int id){
        return  repo.getOrder(id);
    }

    @GetMapping("/history/")
    public List<AdminHistoryOrdersModel> getAllHistoryOrders(){
        return repo.getAllHistoryOrders();
    }
    @GetMapping("/history/{id}")
    public AdminSingleOrderHistoryModel getHistoryOrder(@PathVariable int id){
        return  repo.getHistoryOrder(id);
    }
}
