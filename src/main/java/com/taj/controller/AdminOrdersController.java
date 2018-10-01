package com.taj.controller;

import com.taj.model.*;
import com.taj.model.school_history_admin_dashboard.SchoolOrdersModel;
import com.taj.model.school_history_admin_dashboard.SchoolShipModel;
import com.taj.model.school_history_admin_dashboard.SingleSchoolOrdersModel;
import com.taj.repository.AdminOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AdminOrdersModel> getAllOrders() {
        return repo.getAllOrders();
    }

    @GetMapping("/{id}")
    public AdminSingleOrderModel getOrder(@PathVariable int id) {
        return repo.getOrder(id);
    }

    @GetMapping("/history/")
    public List<AdminHistoryOrdersModel> getAllHistoryOrders() {
        return repo.getAllHistoryOrders();
    }

    @GetMapping("/history/{id}")
    public AdminSingleOrderHistoryModel getHistoryOrder(@PathVariable int id) {
        return repo.getHistoryOrder(id);
    }

    @PostMapping("/ship")
    public int addShipping(@RequestBody ShippingDTO dto) {
        return repo.addShipping(dto.getShip(), dto.getShip_company_offer_id());
    }




    @GetMapping("/school/")
    public List<SchoolOrdersModel> getAllSchoolOrders() {
        return repo.getAllSchoolOrders();
    }

    @GetMapping("/school/{id}")
    public SingleSchoolOrdersModel getSchoolOrder(@PathVariable int id) {
        return repo.getSchoolOrder(id);
    }

    @PostMapping("/school/ship")
    public int addSchoolShipping(@RequestBody SchoolShipModel dto) {
        return repo.addShipping(dto.getShip(), dto.getShip_school_request_id());
    }
}
