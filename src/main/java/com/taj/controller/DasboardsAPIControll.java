package com.taj.controller;

import com.taj.model.AdminHistoryOrdersModel;
import com.taj.repository.AdminOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by User on 9/5/2018.
 */
@RequestMapping("/evvaz")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DasboardsAPIControll {

    @Autowired
    AdminOrdersRepo adminRepo;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("admin/orders/history/")
    public List<AdminHistoryOrdersModel> getAllHistoryOrders(){
        return adminRepo.getAllHistoryOrders();
    }

}
