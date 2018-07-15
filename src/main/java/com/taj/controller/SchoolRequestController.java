package com.taj.controller;

import com.taj.model.SchoolRequestsModel;
import com.taj.repository.SchoolRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MahmoudAhmed on 6/3/2018.
 */
@RequestMapping("/evvaz/school/request")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolRequestController {

    @Autowired
    SchoolRequestRepo repo;

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/addRequest")
    public int addSchoolRequest(@RequestBody SchoolRequestsModel model) {

        return repo.addRequest(model.getRequest_id(), model.getRequest_details_file(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getRequest_deliver_date(), model.getRequest_payment_date(),
                model.getRequest_is_available(), model.getRequest_is_conformied(), model.getSchool_id(), model.getRequest_category_id(), model.getReceive_palce_id(), model.getExtended_payment());
    }

    @GetMapping("/getRequests")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestsModel> getSchoolSingleRequest() {
        return repo.getRequests();
    }

    @GetMapping("/getSchoolRequests/{id}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public List<SchoolRequestsModel> getSchoolRequests(@PathVariable int id) {
        return repo.getRequestsByID(id);
    }

    @GetMapping("/getRequest/{id}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public SchoolRequestsModel getSchoolRequest(@PathVariable int id) {
        return repo.getRequestByID(id);
    }

    @PutMapping("/updateRequest")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public int updateRequestModel(@RequestBody SchoolRequestsModel model) {
        return repo.updateRequest(model.getRequest_id(), model.getRequest_details_file(), model.getRequest_title(), model.getRequest_explaination(),
                model.getRequest_display_date(), model.getRequest_expired_date(), model.getRequest_deliver_date(), model.getRequest_payment_date(),
                model.getRequest_is_available(), model.getRequest_is_conformied(), model.getSchool_id(), model.getRequest_category_id(), model.getReceive_palce_id(), model.getExtended_payment());
    }

    @PutMapping("/deleteRequest/{id}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public int deleteSchoolRequest(@PathVariable int id) {
        return repo.deleteSchoolRequest(id);
    }

    @GetMapping("/filterConfirm/{isConfirmed}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByIsConfirmed(@PathVariable int isConfirmed) {
        return repo.filterByIsConfirmed(isConfirmed);
    }

    @GetMapping("/filterAvailable/{isAvailable}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByIsAvailable(@PathVariable int isAvailable) {
        return repo.filterByIsAvailable(isAvailable);
    }


    @GetMapping("/filterTitle/{title}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByTitle(@PathVariable String title) {
        return repo.filterByTitle(title);
    }

    @GetMapping("/filterExplain/{explain}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByExplain(@PathVariable String explain) {
        return repo.filterByExplain(explain);
    }

    @GetMapping("/filterCat/{cat}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByCategory(@PathVariable int cat) {
        return repo.filterByCategory(cat);
    }

    @GetMapping("/filterPlace/{place}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestsModel> filterByReceivePlace(@PathVariable int place) {
        return repo.filterByReceivePlace(place);
    }

}
