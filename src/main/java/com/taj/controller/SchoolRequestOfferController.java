package com.taj.controller;

import com.taj.model.CompanyResponseSchoolRequestModel;
import com.taj.model.SchoolRequestOfferModel;
import com.taj.repository.SchoolRequestOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by User on 7/8/2018.
 */

@RequestMapping("/evvaz/school/request/offer")
@RestController
@CrossOrigin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SchoolRequestOfferController {

    @Autowired
    SchoolRequestOfferRepo repo;

    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    @PostMapping("/add")
    public int addSchoolRequestOffer(@RequestBody SchoolRequestOfferModel model) {
        return repo.addSchoolRequestOffer(model.getRequest_id(), model.getRequsted_school_id() ,model.getRequsted_offer_id(), model.getIs_accepted());
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getAll")
    public List<SchoolRequestOfferModel> getSchoolRequestsOffer() {
        return repo.getSchoolRequestOffer();
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/get/{id}")
    public SchoolRequestOfferModel getSchoolRequestOffer(@PathVariable int id) {
        return repo.getSchoolRequestOffer(id);
    }

    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    @GetMapping("/getSchool/{schoolId}")
    public List<SchoolRequestOfferModel> getSchoolRequestOfferBySchool(@PathVariable int schoolId) {
        return repo.getSchoolRequestOfferBySchool(schoolId);
    }

    @GetMapping("/getOffer/{offerId}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestOfferModel> SchoolRequestOfferByOffer(@PathVariable int offerId) {
        return repo.getSchoolRequestOfferByOffer(offerId);
    }

    @GetMapping("/approval/{accept}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public List<SchoolRequestOfferModel> SchoolRequestOfferByAprove(@PathVariable int accept) {
        return repo.getSchoolRequestOfferByAccept(accept);
    }

    @PutMapping("/accept/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int acceptSchoolRequestOffer(@PathVariable int id) {
        return repo.acceptSchoolRequestOffer(id);
    }
    @PutMapping("/refuse/{id}")
    @PreAuthorize("hasAuthority('company') or hasAuthority('admin')")
    public int refuseSchoolRequestOffer(@PathVariable int id) {
        return repo.refuseSchoolRequestOffer(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('school') or hasAuthority('admin')")
    public int updateSchoolRequestOffer(@RequestBody SchoolRequestOfferModel model) {
        return repo.updateResponseSchoolRequest(model.getRequest_id(), model.getRequsted_school_id(), model.getRequsted_offer_id(), model.getIs_accepted());
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('school') or hasAuthority('company') or hasAuthority('admin')")
    public int deleteSchoolRequestOffer(@PathVariable int id) {
        return repo.deleteResponseSchoolRequest(id);
    }

}
